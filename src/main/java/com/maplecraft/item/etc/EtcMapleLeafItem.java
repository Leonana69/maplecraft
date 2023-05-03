package com.maplecraft.item.etc;

import com.maplecraft.init.TabsInit;
import com.maplecraft.item.MapleItem;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.utils.MapleRarity;
import net.minecraft.world.item.Item;

public class EtcMapleLeafItem extends MapleItem {
    public static String itemName = "etc_maple_leaf";
    public EtcMapleLeafItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.UNIQUE));
    }
}
