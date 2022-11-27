package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSwordItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.SWORD_SWORD_KV;

public class SwordSwordItem extends WeaponSwordItem {
    public static String itemName = "sword_sword";
    public SwordSwordItem() {
        super(new EquipBaseData()
                .levelReq(SWORD_SWORD_KV.levelReq)
                .addStat("ATTACK", SWORD_SWORD_KV.attack)
                .addStat("ATTACK_SPEED", SWORD_SWORD_KV.attackSpeed));
    }
}
