package net.maplecraft.items;

import net.maplecraft.utils.ScrollItem;
import net.maplecraft.utils.ScrollType;
import net.minecraft.world.item.Rarity;

public class UseLegendaryPotentialScrollItem extends ScrollItem {
    public UseLegendaryPotentialScrollItem() {
        super(new Properties().rarity(Rarity.EPIC), ScrollType.LEGENDARY_POTENTIAL_SCROLL);
    }
}
