package net.maplecraft.items;

import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.CubeType;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.Rarity;

public class UseOccultCubeItem extends CubeItem {
    public UseOccultCubeItem() {
        super(new MapleItemProperties()
                .itemName("use_occult_cube")
                .mapleRarity(MapleRarity.EPIC), CubeType.OCCULT_CUBE);
    }
}
