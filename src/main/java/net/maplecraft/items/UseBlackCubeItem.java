package net.maplecraft.items;

import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.CubeType;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.Rarity;

public class UseBlackCubeItem extends CubeItem {
    public UseBlackCubeItem() {
        super(new MapleItemProperties()
                .itemName("use_black_cube")
                .mapleRarity(MapleRarity.LEGENDARY), CubeType.BLACK_CUBE);
    }
}
