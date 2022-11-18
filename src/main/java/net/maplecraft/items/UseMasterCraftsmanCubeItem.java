package net.maplecraft.items;

import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.CubeType;
import net.minecraft.world.item.Rarity;

public class UseMasterCraftsmanCubeItem extends CubeItem {
    public UseMasterCraftsmanCubeItem() {
        super(new Properties().rarity(Rarity.RARE), CubeType.MASTER_CRAFTSMAN_CUBE);
    }
}
