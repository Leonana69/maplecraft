package net.maplecraft.item;

import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.utils.ScrollItem;
import net.maplecraft.utils.ScrollType;

public class UseLegendaryPotentialScrollItem extends ScrollItem {
    public UseLegendaryPotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName("use_legendary_potential_scroll")
                .mapleRarity(MapleRarity.LEGENDARY), ScrollType.LEGENDARY_POTENTIAL_SCROLL);
    }
}
