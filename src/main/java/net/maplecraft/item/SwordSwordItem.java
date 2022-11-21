package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSwordItem;

public class SwordSwordItem extends WeaponSwordItem {
    public static String itemName = "sword_sword";
    public SwordSwordItem() {
        super(new EquipBaseData()
                .levelReq(0)
                .addStat("ATTACK", 2)
                .addStat("ATTACK_SPEED", 2));
    }
}
