package com.maplecraft.item.use;

import com.maplecraft.item.CubeItem;
import com.maplecraft.item.CubeType;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.utils.MapleRarity;

public class UseRedCubeItem extends CubeItem {
    public static String itemName = "use_red_cube";
    public UseRedCubeItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.LEGENDARY), CubeType.RED_CUBE);
    }
}
