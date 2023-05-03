package com.maplecraft.item.etc;

import com.maplecraft.init.TabsInit;
import com.maplecraft.item.MapleItem;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.utils.MapleRarity;

public class EtcAdvancedMonsterCrystalItem extends MapleItem {
    public static String itemName = "etc_advanced_monster_crystal";
    public EtcAdvancedMonsterCrystalItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.UNIQUE));
    }
}
