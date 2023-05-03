package com.maplecraft.item;

import com.maplecraft.init.TabsInit;
import com.maplecraft.utils.MapleRarity;
import com.maplecraft.utils.TextFormatter;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

public class MapleItem extends Item {
    public String itemName;
    public MapleRarity rarity;

    public MapleItem(MapleItemProperties itemProperties) {
        super(itemProperties.properties != null ? itemProperties.properties : new Properties().tab(TabsInit.TAB_MAPLE_CRAFT));
        rarity = itemProperties.rarity;
        itemName = itemProperties.itemName;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.set(0, Component.literal(TextFormatter.format(itemstack.getHoverName().getString(), rarity.color)));
        String s = "item.maplecraft." + itemName + "_description";
        Component description = Component.translatable(s);
        if (!description.getString().equals(s))
            list.add(description);
    }
}
