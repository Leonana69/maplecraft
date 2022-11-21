package net.maplecraft.item;

import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.utils.ScrollItem;
import net.maplecraft.utils.ScrollType;

public class UseLegendaryPotentialScrollItem extends ScrollItem {
    public static String itemName = "use_legendary_potential_scroll";
    public UseLegendaryPotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.LEGENDARY), ScrollType.LEGENDARY_POTENTIAL_SCROLL);
    }
}
