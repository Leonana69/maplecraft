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

    public WeaponItem(Item.Properties itemProperties, EquipCategory ec, BonusStats bs) {
        super(itemProperties.tab(TabsInit.TAB_MAPLE_CRAFT));
        equipData.category = ec;
        equipData.baseStats = bs;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        appendHoverText(list, equipData);
    }

    @Override
    public List<Component> getTooltip() {
        return equipData.tooltip;
    }
}
