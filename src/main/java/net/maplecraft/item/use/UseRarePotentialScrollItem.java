package net.maplecraft.item.use;

import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.item.ScrollItem;
import net.maplecraft.item.ScrollType;
import net.maplecraft.utils.MapleRarity;

public class UseRarePotentialScrollItem extends ScrollItem {
    public static String itemName = "use_rare_potential_scroll";
    public UseRarePotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.RARE), ScrollType.RARE_POTENTIAL_SCROLL);
    }
}
