package net.maplecraft.item.use;

import net.maplecraft.item.CubeItem;
import net.maplecraft.item.CubeType;
import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;

public class UseRedCubeItem extends CubeItem {
    public static String itemName = "use_red_cube";
    public UseRedCubeItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.LEGENDARY), CubeType.RED_CUBE);
    }
}
