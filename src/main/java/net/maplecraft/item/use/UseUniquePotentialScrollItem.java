package net.maplecraft.item.use;

import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.utils.ScrollItem;
import net.maplecraft.utils.ScrollType;

public class UseUniquePotentialScrollItem extends ScrollItem {
    public static String itemName = "use_unique_potential_scroll";
    public UseUniquePotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.UNIQUE), ScrollType.UNIQUE_POTENTIAL_SCROLL);
    }
}
