package com.maplecraft.item.use;

import com.maplecraft.item.CubeItem;
import com.maplecraft.item.CubeType;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.utils.MapleRarity;

public class UseOccultCubeItem extends CubeItem {
    public static String itemName = "use_occult_cube";
    public UseOccultCubeItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC), CubeType.OCCULT_CUBE);
    }
}
