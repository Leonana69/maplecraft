package com.maplecraft.item.weapon;

import com.maplecraft.item.WeaponSwordItem;
import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.utils.*;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.SWORD_STONETOOTH_SWORD_KV;

public class SwordStonetoothSwordItem extends WeaponSwordItem {
    public static String itemName = "sword_stonetooth_sword";
    public SwordStonetoothSwordItem() {
        super(new EquipBaseData()
                .levelReq(SWORD_STONETOOTH_SWORD_KV.levelReq)
                .addStat("ATTACK", SWORD_STONETOOTH_SWORD_KV.attack)
                .addStat("ATTACK_SPEED", SWORD_STONETOOTH_SWORD_KV.attackSpeed));
    }
}
