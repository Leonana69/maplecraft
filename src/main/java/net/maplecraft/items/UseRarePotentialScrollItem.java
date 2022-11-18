package net.maplecraft.items;

import net.maplecraft.utils.ScrollItem;
import net.maplecraft.utils.ScrollType;
import net.minecraft.world.item.Rarity;

public class UseRarePotentialScrollItem extends ScrollItem {
    public UseRarePotentialScrollItem() {
        super(new Properties().rarity(Rarity.UNCOMMON), ScrollType.RARE_POTENTIAL_SCROLL);
    }
}
