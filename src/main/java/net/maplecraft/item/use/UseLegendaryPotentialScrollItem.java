package net.maplecraft.item.use;

import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.item.ScrollItem;
import net.maplecraft.item.ScrollType;
import net.maplecraft.utils.MapleRarity;

public class UseLegendaryPotentialScrollItem extends ScrollItem {
    public static String itemName = "use_legendary_potential_scroll";
    public UseLegendaryPotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.LEGENDARY), ScrollType.LEGENDARY_POTENTIAL_SCROLL);
    }
}
