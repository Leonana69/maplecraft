package net.maplecraft.item.etc;

import net.maplecraft.init.TabsInit;
import net.maplecraft.item.MapleItem;
import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.Item;

public class EtcMesoMediumItem extends MapleItem {
    public static String itemName = "etc_meso_medium";
    public EtcMesoMediumItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC)
                .properties(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                        .stacksTo(64)));
    }
}
