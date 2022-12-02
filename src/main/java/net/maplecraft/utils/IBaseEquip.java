package net.maplecraft.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.List;

import static net.maplecraft.utils.EquipWiseData.hasEquipWiseData;
import static net.maplecraft.utils.EquipWiseData.initFromCompoundTag;

/* Because our armor equips have to be extended from ArmorItem, this is made as an interface */
public interface IBaseEquip {
    EquipBaseData getBaseEquipData();
    EquipCategory getCategory();

    default void setStarForce(ItemStack itemStack, int starForce) {
        // TODO:
    }

    default void setPotential(ItemStack itemStack, MapleRarity rarity, PotentialStats[] potentialStats) {
        EquipWiseData data = getEquipWiseData(itemStack);
        data.equipRarity = rarity;
        data.potentials = potentialStats;
        setEquipWiseData(itemStack, data);
    }

    default MapleRarity getPotentialRarity(ItemStack itemStack) {
        return getEquipWiseData(itemStack).equipRarity;
    }

    default boolean hasPotential(ItemStack itemStack) {
        return getEquipWiseData(itemStack).equipRarity != MapleRarity.COMMON;
    }

    default List<Component> getTooltip(ItemStack itemStack) {
        return EquipWiseData.componentFromString(getEquipWiseData(itemStack).tooltip);
    }

    default EquipWiseData getEquipWiseData(ItemStack itemStack) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        if (!hasEquipWiseData(compoundTag)) {
            new EquipWiseData().addToCompoundTag(compoundTag);
        }

        return initFromCompoundTag(compoundTag);
    }

    static void setEquipWiseData(ItemStack itemStack, EquipWiseData data) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        data.addToCompoundTag(compoundTag);
    }

    default void appendHoverText(ItemStack itemStack, List<Component> list, EquipBaseData data) {
        EquipWiseData eData = getEquipWiseData(itemStack);
        list.clear();
        // star force
        char [] cur_star = new char[eData.starForce];
        char [] empty_star = new char[EquipBaseData.max_star_force - eData.starForce];
        Arrays.fill(cur_star, '★');
        Arrays.fill(empty_star, '☆');
        list.add(Component.literal(TextFormatter.format(new String(cur_star), ChatFormatting.YELLOW) +
                TextFormatter.format(new String(empty_star), ChatFormatting.WHITE)));

        list.add(Component.literal(TextFormatter.format(itemStack.getHoverName().getString(), eData.equipRarity.color)));

        // level
        list.add(Component.literal(
                 Component.translatable("utils.maplecraft.base_equip_item_level").getString() + data.levelReq));
        list.add(Component.translatable("utils.maplecraft.base_equip_item_divider"));
        // category
        list.add(Component.literal(
            Component.translatable("utils.maplecraft.base_equip_item_category").getString()
            + data.category.typeName));

        // baseStats
        // do not show attack speed
        for (int i = 1; i < BaseStats.valueTypes; i++) {
            int value = data.baseStats.values[i];
            if (value > 0) {
                list.add(Component.literal(BaseStats.valuesName.get(i) + ": +" + value));
            }
        }

        if (data.canGetPotential) {
            // potential
            list.add(Component.translatable("utils.maplecraft.base_equip_item_divider"));
            list.add(Component.literal(TextFormatter.format(eData.equipRarity.typeName
                    + Component.translatable("utils.maplecraft.base_equip_item_potential").getString(), eData.equipRarity.color)));
            if (eData.equipRarity == MapleRarity.COMMON) {
                list.add(Component.translatable("utils.maplecraft.base_equip_item_potential_common"));
            } else {
                for (int i = 0; i < 3; i++) {
                    PotentialStats ps = eData.potentials[i];
                    if (ps.rarity != MapleRarity.COMMON) {
                        list.add(Component.literal(ps.toString()));
                    }
                }
            }
        }

        eData.tooltip = EquipWiseData.componentToString(list);
        setEquipWiseData(itemStack, eData);
    }
}