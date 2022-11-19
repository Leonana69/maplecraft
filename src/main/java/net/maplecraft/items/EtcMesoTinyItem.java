package net.maplecraft.items;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.MapleItem;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class EtcMesoTinyItem extends MapleItem {
    public EtcMesoTinyItem() {
        super(new MapleItemProperties()
                .itemName("etc_meso_tiny")
                .mapleRarity(MapleRarity.COMMON)
                .properties(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                        .stacksTo(64)));
    }
}
