package net.maplecraft.items;

import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.CubeType;
import net.minecraft.world.item.Rarity;

public class UseBlackCubeItem extends CubeItem {
    public UseBlackCubeItem() {
        super(new Properties().rarity(Rarity.EPIC), CubeType.BLACK_CUBE);
    }
}
