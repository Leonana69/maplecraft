package com.maplecraft.item.etc;

import com.maplecraft.init.TabsInit;
import com.maplecraft.item.MapleItem;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.Item;

public class EtcMesoSmallItem extends MapleItem {
    public static String itemName = "etc_meso_small";
    public EtcMesoSmallItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.RARE));
    }
}
