package net.maplecraft.item;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.MapleItem;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;

public class EtcEyeOfFireItem extends MapleItem {
    public static String itemName = "etc_eye_of_fire";
    public EtcEyeOfFireItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.LEGENDARY)
                .properties(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                        .stacksTo(1)));
    }
}
