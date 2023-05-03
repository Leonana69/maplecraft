package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponCrossbowItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.CROSSBOW_CROSSBOW_KV;

public class CrossbowCrossbowItem extends WeaponCrossbowItem {
    public static String itemName = "crossbow_crossbow";
    public CrossbowCrossbowItem() {
        super(new EquipBaseData()
                .levelReq(CROSSBOW_CROSSBOW_KV.levelReq)
                .addStat("ATTACK", CROSSBOW_CROSSBOW_KV.attack)
                .addStat("ATTACK_SPEED", CROSSBOW_CROSSBOW_KV.attackSpeed));
    }
}
