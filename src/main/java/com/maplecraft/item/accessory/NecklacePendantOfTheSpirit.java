package com.maplecraft.item.accessory;

import com.maplecraft.item.MapleAccessoryItem;
import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.utils.EquipCategory;

public class NecklacePendantOfTheSpirit extends MapleAccessoryItem {
    public static String itemName = "necklace_pendant_of_the_spirit";
    public NecklacePendantOfTheSpirit() {
        super(new EquipBaseData().setCanGetPotential(false)
                .category(EquipCategory.NECKLACE)
                .addStat("ATTACK", 1));
    }
}
