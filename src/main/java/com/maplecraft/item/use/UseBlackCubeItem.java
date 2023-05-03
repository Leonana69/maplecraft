package com.maplecraft.item.use;

import com.maplecraft.item.CubeItem;
import com.maplecraft.item.CubeType;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.utils.MapleRarity;

public class UseBlackCubeItem extends CubeItem {
    public static String itemName = "use_black_cube";
    public UseBlackCubeItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.LEGENDARY), CubeType.BLACK_CUBE);
    }
}
