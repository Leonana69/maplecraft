package net.maplecraft.items;

import net.maplecraft.utils.ScrollItem;
import net.maplecraft.utils.ScrollType;
import net.minecraft.world.item.Rarity;

public class UseUniquePotentialScrollItem extends ScrollItem {
    public UseUniquePotentialScrollItem() {
        super(new Properties().rarity(Rarity.EPIC), ScrollType.UNIQUE_POTENTIAL_SCROLL);
    }
}
