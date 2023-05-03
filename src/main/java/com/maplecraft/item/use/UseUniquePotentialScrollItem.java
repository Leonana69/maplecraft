package com.maplecraft.item.use;

import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.item.ScrollItem;
import com.maplecraft.item.ScrollType;
import com.maplecraft.utils.MapleRarity;

public class UseUniquePotentialScrollItem extends ScrollItem {
    public static String itemName = "use_unique_potential_scroll";
    public UseUniquePotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.UNIQUE), ScrollType.UNIQUE_POTENTIAL_SCROLL);
    }
}
