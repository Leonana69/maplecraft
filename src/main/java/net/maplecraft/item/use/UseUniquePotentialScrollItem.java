package net.maplecraft.item.use;

import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.item.ScrollItem;
import net.maplecraft.item.ScrollType;
import net.maplecraft.utils.MapleRarity;

public class UseUniquePotentialScrollItem extends ScrollItem {
    public static String itemName = "use_unique_potential_scroll";
    public UseUniquePotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.UNIQUE), ScrollType.UNIQUE_POTENTIAL_SCROLL);
    }
}
