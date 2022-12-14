package net.maplecraft.item.etc;

import net.maplecraft.init.TabsInit;
import net.maplecraft.item.MapleItem;
import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.Item;

public class EtcMesoLargeItem extends MapleItem {
    public static String itemName = "etc_meso_large";
    public EtcMesoLargeItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.UNIQUE)
                .properties(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                        .stacksTo(64)));
    }
}
