package net.maplecraft.item.use;

import net.maplecraft.item.CubeItem;
import net.maplecraft.item.CubeType;
import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;

public class UseBlackCubeItem extends CubeItem {
    public static String itemName = "use_black_cube";
    public UseBlackCubeItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.LEGENDARY), CubeType.BLACK_CUBE);
    }
}
