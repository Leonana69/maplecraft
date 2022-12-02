package net.maplecraft.item.use;

import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.maplecraft.utils.ScrollItem;
import net.maplecraft.utils.ScrollType;

public class UseEpicPotentialScrollItem extends ScrollItem {
    public static String itemName = "use_epic_potential_scroll";
    public UseEpicPotentialScrollItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC), ScrollType.EPIC_POTENTIAL_SCROLL);
    }
}
