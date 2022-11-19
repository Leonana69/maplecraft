package net.maplecraft.items;

import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.utils.ScrollItem;
import net.maplecraft.utils.ScrollType;
import net.minecraft.world.item.Rarity;

public class UseUniquePotentialScrollItem extends ScrollItem {
    public UseUniquePotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName("use_unique_potential_scroll")
                .mapleRarity(MapleRarity.UNIQUE), ScrollType.UNIQUE_POTENTIAL_SCROLL);
    }
}
