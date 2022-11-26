package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSwordItem;

public class SwordSabreItem extends WeaponSwordItem {
    public static String itemName = "sword_sabre";
    public SwordSabreItem() {
        super(new EquipBaseData()
                .levelReq(5)
                .addStat("ATTACK", 4)
                .addStat("ATTACK_SPEED", 2));
    }
}
