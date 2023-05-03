package com.maplecraft.item.use;

import com.maplecraft.item.CubeItem;
import com.maplecraft.item.CubeType;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.utils.MapleRarity;

public class UseMasterCraftsmanCubeItem extends CubeItem {
    public static String itemName = "use_master_craftsman_cube";
    public UseMasterCraftsmanCubeItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.UNIQUE), CubeType.MASTER_CRAFTSMAN_CUBE);
    }
}
