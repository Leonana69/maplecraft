package net.maplecraft.utils;

import net.maplecraft.init.TabsInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class WeaponItem extends Item implements BaseEquipInterface {
    public BaseEquipData equipData = new BaseEquipData();

    public WeaponItem(Properties properties, EquipCategory ec, BonusStats bs) {
        super(properties.tab(TabsInit.TAB_MAPLE_CRAFT));
        equipData.category = ec;
        equipData.baseStats = bs;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        appendHoverText(list, equipData, getEquipWiseData());
    }

    @Override
    public boolean hasPotential() {
        return getEquipWiseData().rarity != PotentialRarity.COMMON;
    }

    @Override
    public PotentialRarity getPotentialRarity() {
        return getEquipWiseData().rarity;
    }

    @Override
    public void setPotential(PotentialRarity rarity, PotentialType[] potentialTypes) {}

    @Override
    public void setStarForce(int starForce) {}

    @Override
    public EquipWiseData getEquipWiseData() {
        return null;
    }

    @Override
    public EquipCategory getCategory() {
        return equipData.category;
    }
}
