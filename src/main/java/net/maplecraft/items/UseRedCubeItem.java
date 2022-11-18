package net.maplecraft.items;

import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.CubeType;
import net.minecraft.world.item.Rarity;

public class UseRedCubeItem extends CubeItem {
    public UseRedCubeItem() {
        super(new Properties().rarity(Rarity.EPIC), CubeType.RED_CUBE);
    }
}
