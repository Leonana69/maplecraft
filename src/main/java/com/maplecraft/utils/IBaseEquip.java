package com.maplecraft.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

import static com.maplecraft.utils.EquipWiseData.hasEquipWiseData;
import static com.maplecraft.utils.EquipWiseData.initFromCompoundTag;

/* Because our armor equips have to be extended from ArmorItem, this is made as an interface */
public interface IBaseEquip {
    EquipBaseData getBaseEquipData();

    default void setStarForce(ItemStack itemStack, int starForce) {
        // TODO: add star force
    }

    default boolean meetLevelReq(Player player) {
        return player.experienceLevel >= getBaseEquipData().levelReq;
    }

    default void setNewPotential(ItemStack itemStack, MapleRarity rarity, PotentialStats[] potentialStats) {
        EquipWiseData data = getEquipWiseData(itemStack);
        data.equipRarityNew = rarity;
        data.potentialsNew = potentialStats;
        setEquipWiseData(itemStack, data);
    }

    default void updatePotential(ItemStack itemStack) {
        EquipWiseData data = getEquipWiseData(itemStack);
        data.updatePotential();
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

    default void appendHoverText(ItemStack itemStack, Level level, List<Component> list, EquipBaseData data) {
        EquipWiseData eData = getEquipWiseData(itemStack);
        list.clear();
        // star force
        // TODO: add star force
        /*
        char [] cur_star = new char[eData.starForce];
        char [] empty_star = new char[EquipBaseData.max_star_force - eData.starForce];
        Arrays.fill(cur_star, '★');
        Arrays.fill(empty_star, '☆');
        list.add(Component.literal(TextFormatter.format(new String(cur_star), ChatFormatting.YELLOW) +
                TextFormatter.format(new String(empty_star), ChatFormatting.WHITE)));
        */
        list.add(Component.literal(TextFormatter.format(itemStack.getHoverName().getString(), eData.equipRarity.color)));

        // levelReq
        ChatFormatting levelReqColor = ChatFormatting.WHITE;
        // TODO: fix client
//        if (level.isClientSide) {
//            Player player = Minecraft.getInstance().player;
//            if (player != null && data.levelReq > player.experienceLevel) {
//                levelReqColor = ChatFormatting.RED;
//            }
//        }

        list.add(Component.literal(TextFormatter.format(
                 Component.translatable("utils.maplecraft.base_equip_item_level").getString() + data.levelReq,
                levelReqColor)));

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
                list.add(Component.literal(BaseStats.valuesName.get(i) + ": +" + value + BaseStats.suffix.get(i)));
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