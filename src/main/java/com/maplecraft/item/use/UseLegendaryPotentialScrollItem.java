package com.maplecraft.item.use;

import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.item.ScrollItem;
import com.maplecraft.item.ScrollType;
import com.maplecraft.utils.MapleRarity;

public class UseLegendaryPotentialScrollItem extends ScrollItem {
    public static String itemName = "use_legendary_potential_scroll";
    public UseLegendaryPotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.LEGENDARY), ScrollType.LEGENDARY_POTENTIAL_SCROLL);
    }
}
