package net.maplecraft.items;

import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.CubeType;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.Rarity;

public class UseMasterCraftsmanCubeItem extends CubeItem {
    public UseMasterCraftsmanCubeItem() {
        super(new MapleItemProperties()
                .itemName("use_master_craftsman_cube")
                .mapleRarity(MapleRarity.UNIQUE), CubeType.MASTER_CRAFTSMAN_CUBE);
    }
}
