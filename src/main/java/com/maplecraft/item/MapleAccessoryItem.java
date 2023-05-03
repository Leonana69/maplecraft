package com.maplecraft.item;

import com.maplecraft.init.TabsInit;
import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.utils.IBaseEquip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class MapleAccessoryItem extends Item implements IBaseEquip, ICurioItem {
    public EquipBaseData baseEquipData;

    public MapleAccessoryItem(EquipBaseData data) {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT));
        this.baseEquipData = data;
    }

    @Override
    public EquipBaseData getBaseEquipData() {
        return baseEquipData;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemStack, level, list, flag);
        appendHoverText(itemStack, level, list, baseEquipData);
    }
}
