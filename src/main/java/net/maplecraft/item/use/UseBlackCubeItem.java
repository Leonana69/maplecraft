package net.maplecraft.item.use;

import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.CubeType;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;

public class UseBlackCubeItem extends CubeItem {
    public static String itemName = "use_black_cube";
    public UseBlackCubeItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.LEGENDARY), CubeType.BLACK_CUBE);
    }
}
