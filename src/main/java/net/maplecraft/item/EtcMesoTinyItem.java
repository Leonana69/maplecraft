package net.maplecraft.item;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.MapleItem;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.*;

public class EtcMesoTinyItem extends MapleItem {
    public EtcMesoTinyItem() {
        super(new MapleItemProperties()
                .itemName("etc_meso_tiny")
                .mapleRarity(MapleRarity.COMMON)
                .properties(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                        .stacksTo(64)));
    }
}
