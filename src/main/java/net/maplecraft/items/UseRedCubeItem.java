package net.maplecraft.items;

import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.CubeType;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.Rarity;

public class UseRedCubeItem extends CubeItem {
    public UseRedCubeItem() {
        super(new MapleItemProperties()
                .itemName("use_red_cube")
                .mapleRarity(MapleRarity.LEGENDARY), CubeType.RED_CUBE);
    }
}
