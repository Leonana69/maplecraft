package net.maplecraft.item.accessory;

import net.maplecraft.utils.AccessoryItem;
import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.EquipCategory;

public class NecklacePendantOfTheSpirit extends AccessoryItem {
    public static String itemName = "necklace_pendant_of_the_spirit";
    public NecklacePendantOfTheSpirit() {
        super(new EquipBaseData().setCanGetPotential(false)
                .category(EquipCategory.NECKLACE)
                .addStat("ATTACK", 1));
    }
}
