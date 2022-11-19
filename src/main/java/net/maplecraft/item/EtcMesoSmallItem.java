package net.maplecraft.item;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.MapleItem;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.Item;

public class EtcMesoSmallItem extends MapleItem {
    public EtcMesoSmallItem() {
        super(new MapleItemProperties()
                .itemName("etc_meso_small")
                .mapleRarity(MapleRarity.RARE)
                .properties(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                        .stacksTo(64)));
    }
}
