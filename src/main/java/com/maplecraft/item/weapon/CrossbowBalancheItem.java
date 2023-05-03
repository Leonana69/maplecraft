package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponCrossbowItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.CROSSBOW_BALANCHE_KV;

public class CrossbowBalancheItem extends WeaponCrossbowItem {
    public static String itemName = "crossbow_balanche";
    public CrossbowBalancheItem() {
        super(new EquipBaseData()
                .levelReq(CROSSBOW_BALANCHE_KV.levelReq)
                .addStat("ATTACK", CROSSBOW_BALANCHE_KV.attack)
                .addStat("ATTACK_SPEED", CROSSBOW_BALANCHE_KV.attackSpeed));
    }
}
