package net.maplecraft.item.use;

import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.item.ScrollItem;
import net.maplecraft.item.ScrollType;
import net.maplecraft.utils.MapleRarity;

public class UseEpicPotentialScrollItem extends ScrollItem {
    public static String itemName = "use_epic_potential_scroll";
    public UseEpicPotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC), ScrollType.EPIC_POTENTIAL_SCROLL);
    }
}
