package net.maplecraft.items;

import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.CubeType;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.Rarity;

public class UseMeisterCubeItem extends CubeItem {
    public UseMeisterCubeItem() {
        super(new MapleItemProperties()
                .itemName("use_meister_cube")
                .mapleRarity(MapleRarity.LEGENDARY), CubeType.MEISTER_CUBE);
    }
}
