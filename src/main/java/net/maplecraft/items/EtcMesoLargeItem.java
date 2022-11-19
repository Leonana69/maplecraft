package net.maplecraft.items;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.MapleItem;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.Item;

public class EtcMesoLargeItem extends MapleItem {
    public EtcMesoLargeItem() {
        super(new MapleItemProperties()
                .itemName("etc_meso_large")
                .mapleRarity(MapleRarity.UNIQUE)
                .properties(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                        .stacksTo(64)));
    }
}
