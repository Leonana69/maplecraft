package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSpearItem;

public class SpearSpearItem extends WeaponSpearItem {
    public static String itemName = "spear_spear";
    public SpearSpearItem() {
        super(new EquipBaseData()
                .levelReq(0)
                .addStat("ATTACK", 3)
                .addStat("ATTACK_SPEED", 4));
    }
}
