package net.maplecraft.items;

import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.utils.ScrollItem;
import net.maplecraft.utils.ScrollType;
import net.minecraft.world.item.Rarity;

public class UseEpicPotentialScrollItem extends ScrollItem {
    public UseEpicPotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName("use_epic_potential_scroll")
                .mapleRarity(MapleRarity.EPIC), ScrollType.EPIC_POTENTIAL_SCROLL);
    }
}
