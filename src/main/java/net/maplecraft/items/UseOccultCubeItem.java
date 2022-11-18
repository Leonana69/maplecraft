package net.maplecraft.items;

import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.CubeType;
import net.minecraft.world.item.Rarity;

public class UseOccultCubeItem extends CubeItem {
    public UseOccultCubeItem() {
        super(new Properties().rarity(Rarity.UNCOMMON), CubeType.OCCULT_CUBE);
    }
}
