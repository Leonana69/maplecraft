package net.maplecraft.items;

import net.maplecraft.utils.ScrollItem;
import net.maplecraft.utils.ScrollType;
import net.minecraft.world.item.Rarity;

public class UseEpicPotentialScrollItem extends ScrollItem {
    public UseEpicPotentialScrollItem() {
        super(new Properties().rarity(Rarity.RARE), ScrollType.EPIC_POTENTIAL_SCROLL);
    }
}
