package net.maplecraft.item.etc;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.MapleItem;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;

public class EtcLostGoldToothItem extends MapleItem {
    public static String itemName = "etc_lost_gold_tooth";
    public EtcLostGoldToothItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC)
                .properties(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                        .stacksTo(64)));
    }
}
