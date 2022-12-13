package net.maplecraft.item.etc;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.MapleItem;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;

public class EtcBasicEquipCrystalItem extends MapleItem {
    public static String itemName = "etc_basic_equip_crystal";
    public EtcBasicEquipCrystalItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.COMMON)
                .properties(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                        .stacksTo(64)));
    }
}
