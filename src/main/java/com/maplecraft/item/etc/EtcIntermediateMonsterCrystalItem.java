package com.maplecraft.item.etc;

import com.maplecraft.init.TabsInit;
import com.maplecraft.item.MapleItem;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.utils.MapleRarity;

public class EtcIntermediateMonsterCrystalItem extends MapleItem {
    public static String itemName = "etc_intermediate_monster_crystal";
    public EtcIntermediateMonsterCrystalItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.RARE));
    }
}
