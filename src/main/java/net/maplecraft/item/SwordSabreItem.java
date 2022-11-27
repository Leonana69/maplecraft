package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSwordItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.SWORD_SABRE_KV;

public class SwordSabreItem extends WeaponSwordItem {
    public static String itemName = "sword_sabre";
    public SwordSabreItem() {
        super(new EquipBaseData()
                .levelReq(SWORD_SABRE_KV.levelReq)
                .addStat("ATTACK", SWORD_SABRE_KV.attack)
                .addStat("ATTACK_SPEED", SWORD_SABRE_KV.attackSpeed));
    }
}
