package com.maplecraft.item.use;

import com.maplecraft.item.CubeItem;
import com.maplecraft.item.CubeType;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.utils.MapleRarity;

public class UseMeisterCubeItem extends CubeItem {
    public static String itemName = "use_meister_cube";
    public UseMeisterCubeItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.LEGENDARY), CubeType.MEISTER_CUBE);
    }
}
