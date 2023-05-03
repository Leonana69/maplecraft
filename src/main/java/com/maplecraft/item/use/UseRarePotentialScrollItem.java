package com.maplecraft.item.use;

import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.item.ScrollItem;
import com.maplecraft.item.ScrollType;
import com.maplecraft.utils.MapleRarity;

public class UseRarePotentialScrollItem extends ScrollItem {
    public static String itemName = "use_rare_potential_scroll";
    public UseRarePotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.RARE), ScrollType.RARE_POTENTIAL_SCROLL);
    }
}
