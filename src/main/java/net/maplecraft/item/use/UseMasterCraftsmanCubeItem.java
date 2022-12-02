package net.maplecraft.item.use;

import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.CubeType;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;

public class UseMasterCraftsmanCubeItem extends CubeItem {
    public static String itemName = "use_master_craftsman_cube";
    public UseMasterCraftsmanCubeItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.UNIQUE), CubeType.MASTER_CRAFTSMAN_CUBE);
    }
}
