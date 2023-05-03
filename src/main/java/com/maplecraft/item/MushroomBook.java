package com.maplecraft.item;

import com.maplecraft.init.TabsInit;
import com.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.Item;

public class MushroomBook extends MapleItem {
    public static String itemName = "mushroom_book";
    public MushroomBook() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.COMMON)
                .properties(new Properties().stacksTo(1)));
    }
}
