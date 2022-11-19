package net.maplecraft.item;

import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.utils.ScrollItem;
import net.maplecraft.utils.ScrollType;

public class UseRarePotentialScrollItem extends ScrollItem {
    public UseRarePotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName("use_rare_potential_scroll")
                .mapleRarity(MapleRarity.RARE), ScrollType.RARE_POTENTIAL_SCROLL);
    }
}
