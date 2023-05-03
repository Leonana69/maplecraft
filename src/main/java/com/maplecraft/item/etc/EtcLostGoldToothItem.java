package com.maplecraft.item.etc;

import com.maplecraft.item.MapleItem;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.utils.MapleRarity;
import com.maplecraft.init.TabsInit;

public class EtcLostGoldToothItem extends MapleItem {
    public static String itemName = "etc_lost_gold_tooth";
    public EtcLostGoldToothItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC));
    }
}
