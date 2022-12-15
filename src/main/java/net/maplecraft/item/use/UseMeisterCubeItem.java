package net.maplecraft.item.use;

import net.maplecraft.item.CubeItem;
import net.maplecraft.item.CubeType;
import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;

public class UseMeisterCubeItem extends CubeItem {
    public static String itemName = "use_meister_cube";
    public UseMeisterCubeItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.LEGENDARY), CubeType.MEISTER_CUBE);
    }
}
