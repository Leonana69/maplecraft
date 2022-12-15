package net.maplecraft.item.use;

import net.maplecraft.item.CubeItem;
import net.maplecraft.item.CubeType;
import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;

public class UseOccultCubeItem extends CubeItem {
    public static String itemName = "use_occult_cube";
    public UseOccultCubeItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC), CubeType.OCCULT_CUBE);
    }
}
