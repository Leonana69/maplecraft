package net.maplecraft.utils;

import net.maplecraft.init.TabsInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class AccessoryItem extends Item implements IBaseEquip, ICurioItem {
    public EquipBaseData baseEquipData;

    public AccessoryItem(EquipBaseData data) {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT));
        this.baseEquipData = data;
    }

    @Override
    public EquipBaseData getBaseEquipData() {
        return baseEquipData;
    }

    @Override
    public EquipCategory getCategory() {
        return baseEquipData.category;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemStack, world, list, flag);
        appendHoverText(itemStack, list, baseEquipData);
    }
}
