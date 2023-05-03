package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponSpearItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.SPEAR_SPEAR_KV;

public class SpearSpearItem extends WeaponSpearItem {
    public static String itemName = "spear_spear";
    public SpearSpearItem() {
        super(new EquipBaseData()
                .levelReq(SPEAR_SPEAR_KV.levelReq)
                .addStat("ATTACK", SPEAR_SPEAR_KV.attack)
                .addStat("ATTACK_SPEED", SPEAR_SPEAR_KV.attackSpeed));
    }
}
