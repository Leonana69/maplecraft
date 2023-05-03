package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponSwordItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.SWORD_SABRE_KV;

public class SwordSabreItem extends WeaponSwordItem {
    public static String itemName = "sword_sabre";
    public SwordSabreItem() {
        super(new EquipBaseData()
                .levelReq(SWORD_SABRE_KV.levelReq)
                .addStat("ATTACK", SWORD_SABRE_KV.attack)
                .addStat("ATTACK_SPEED", SWORD_SABRE_KV.attackSpeed));
    }
}
