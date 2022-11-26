package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSpearItem;

public class SpearFishSpearItem extends WeaponSpearItem {
    public static String itemName = "spear_fish_spear";
    public SpearFishSpearItem() {
        super(new EquipBaseData()
                .levelReq(10)
                .addStat("ATTACK", 7)
                .addStat("ATTACK_SPEED", 4));
    }
}
