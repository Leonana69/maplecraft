package com.maplecraft.item.use;

import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.item.ScrollItem;
import com.maplecraft.item.ScrollType;
import com.maplecraft.utils.MapleRarity;

public class UseEpicPotentialScrollItem extends ScrollItem {
    public static String itemName = "use_epic_potential_scroll";
    public UseEpicPotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC), ScrollType.EPIC_POTENTIAL_SCROLL);
    }
}
