package com.maplecraft.item.etc;

import com.maplecraft.init.TabsInit;
import com.maplecraft.item.MapleItem;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.utils.MapleRarity;

public class EtcBasicMonsterCrystalItem extends MapleItem {
    public static String itemName = "etc_basic_monster_crystal";
    public EtcBasicMonsterCrystalItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.COMMON));
    }
}
