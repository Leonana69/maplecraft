package net.maplecraft.item.etc;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.MapleItem;
import net.maplecraft.utils.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;

public class EtcIntermediateEquipCrystalItem extends MapleItem {
    public static String itemName = "etc_intermediate_equip_crystal";
    public EtcIntermediateEquipCrystalItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.RARE)
                .properties(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                        .stacksTo(64)));
    }
}
