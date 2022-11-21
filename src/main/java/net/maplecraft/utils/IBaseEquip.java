package net.maplecraft.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.List;

import static net.maplecraft.network.EquipCapabilitiesProvider.EQUIP_CAPABILITIES;

/* Because our armor equips have to be extended from ArmorItem, this is made as an interface */
public interface IBaseEquip {
    boolean hasPotential(ItemStack itemstack);
    List<Component> getTooltip(ItemStack itemstack);
    MapleRarity getPotentialRarity(ItemStack itemstack);
    EquipBaseData getBaseEquipData();
    void setPotential(ItemStack itemstack, MapleRarity rarity, PotentialStats [] potentialStats);
    void setStarForce(ItemStack itemstack, int starForce);
    EquipCategory getCategory();

    default EquipWiseData getEquipWiseData(ItemStack itemStack) {
        return itemStack.getCapability(EQUIP_CAPABILITIES).orElse(new EquipWiseData());
    }

    default void appendHoverText(ItemStack itemStack, List<Component> list, EquipBaseData data) {
        EquipWiseData eData = getEquipWiseData(itemStack);
        list.clear();
        // star force
        char [] cur_star = new char[eData.starForce];
        char [] empty_star = new char[data.max_star_force - eData.starForce];
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

        eData.tooltip = EquipWiseData.componentToString(list);
    }
}