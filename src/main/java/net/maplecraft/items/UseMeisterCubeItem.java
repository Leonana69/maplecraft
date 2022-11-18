package net.maplecraft.items;

import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.CubeType;
import net.minecraft.world.item.Rarity;

public class UseMeisterCubeItem extends CubeItem {
    public UseMeisterCubeItem() {
        super(new Properties().rarity(Rarity.EPIC), CubeType.MEISTER_CUBE);
    }
}
