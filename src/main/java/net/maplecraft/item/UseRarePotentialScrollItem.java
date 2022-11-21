package net.maplecraft.item;

import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.utils.ScrollItem;
import net.maplecraft.utils.ScrollType;

public class UseRarePotentialScrollItem extends ScrollItem {
    public static String itemName = "use_rare_potential_scroll";
    public UseRarePotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.RARE), ScrollType.RARE_POTENTIAL_SCROLL);
    }
}
