package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponSpearItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.SPEAR_MAPLE_IMPALER_KV;

public class SpearMapleImpalerItem extends WeaponSpearItem {
    public static String itemName = "spear_maple_impaler";
    public SpearMapleImpalerItem() {
        super(new EquipBaseData()
                .levelReq(SPEAR_MAPLE_IMPALER_KV.levelReq)
                .addStat("ATTACK", SPEAR_MAPLE_IMPALER_KV.attack)
                .addStat("ATTACK_SPEED", SPEAR_MAPLE_IMPALER_KV.attackSpeed));
    }
}
