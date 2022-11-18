package net.maplecraft.utils;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.maplecraft.network.EquipCapabilitiesProvider.EQUIP_CAPABILITIES;
import static net.maplecraft.utils.PotentialType.getPotentialAsString;

public interface IBaseEquip {
    boolean hasPotential(ItemStack itemstack);
    List<Component> getTooltip(ItemStack itemstack);
    PotentialRarity getPotentialRarity(ItemStack itemstack);
    void setPotential(ItemStack itemstack, PotentialRarity rarity, PotentialType[] potentialTypes);
    void setStarForce(ItemStack itemstack, int starForce);
    EquipCategory getCategory();

    default EquipWiseData getEquipWiseData(ItemStack itemStack) {
        return itemStack.getCapability(EQUIP_CAPABILITIES).orElse(new EquipWiseData());
    }

    default void appendHoverText(List<Component> list, BaseEquipData data, EquipWiseData eData) {
        // star force
        char [] cur_star = new char[eData.starForce];
        char [] empty_star = new char[data.max_star_force - eData.starForce];
        Arrays.fill(cur_star, '★');
        Arrays.fill(empty_star, '☆');
        list.add(Component.literal(TextFormatter.format(new String(cur_star), ChatFormatting.YELLOW) +
                TextFormatter.format(new String(empty_star), ChatFormatting.WHITE)));

        // description
        list.add(Component.literal(data.description));

        // category
        list.add(Component.literal(
            Component.translatable("utils.maplecraft.base_equip_item_category").getString()
            + data.category.typeName));

        // baseStats
        for (int i = 0; i < data.baseStats.valueTypes; i++) {
            int value = data.baseStats.values[i];
            if (value > 0) {
                list.add(Component.literal(BonusStats.valuesName.get(i) + ": +" + value));
            }
        }

        // potential
        list.add(Component.translatable("utils.maplecraft.base_equip_item_divider"));
        list.add(Component.literal(TextFormatter.format(eData.rarity.typeName
            + Component.translatable("utils.maplecraft.base_equip_item_potential").getString(), eData.rarity.color)));
        if (eData.rarity == PotentialRarity.COMMON) {
            list.add(Component.translatable("utils.maplecraft.base_equip_item_potential_common"));
        } else {
            for (int i = 0; i < 3; i++) {
                PotentialType pt = eData.potentials[i];
                if (pt != PotentialType.NONE) {
                    list.add(Component.literal(getPotentialAsString(pt, eData.rarity)));
                }
            }
        }

        eData.tooltip = EquipWiseData.toString(new ArrayList<>(list));
    }
}