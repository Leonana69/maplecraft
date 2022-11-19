package net.maplecraft.item;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.MapleItem;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.Item;

public class EtcMapleLeafItem extends MapleItem {
    public EtcMapleLeafItem() {
        super(new MapleItemProperties()
                .itemName("etc_maple_leaf")
                .mapleRarity(MapleRarity.UNIQUE)
                .properties(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                        .stacksTo(64)));
    }
}
