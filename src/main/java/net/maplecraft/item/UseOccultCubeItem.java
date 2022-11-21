package net.maplecraft.item;

import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.CubeType;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;

public class UseOccultCubeItem extends CubeItem {
    public static String itemName = "use_occult_cube";
    public UseOccultCubeItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC), CubeType.OCCULT_CUBE);
    }
}
