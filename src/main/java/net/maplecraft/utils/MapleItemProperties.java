package net.maplecraft.utils;

import net.minecraft.world.item.Item;

public class MapleItemProperties {
    MapleRarity rarity;
    String itemName;
    Item.Properties properties;

    public MapleItemProperties mapleRarity(MapleRarity r) {
        rarity = r;
        return this;
    }

    public MapleItemProperties itemName(String s) {
        itemName = s;
        return this;
    }

    public MapleItemProperties properties(Item.Properties p) {
        properties = p;
        return this;
    }
}
