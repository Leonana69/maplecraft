package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponWandItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.WAND_MAPLE_LAMA_STAFF_KV;

public class WandMapleLamaStaffItem extends WeaponWandItem {
    public static String itemName = "wand_maple_lama_staff";
    public WandMapleLamaStaffItem() {
        super(new EquipBaseData()
                .levelReq(WAND_MAPLE_LAMA_STAFF_KV.levelReq)
                .addStat("M.ATTACK", WAND_MAPLE_LAMA_STAFF_KV.attack)
                .addStat("ATTACK_SPEED", WAND_MAPLE_LAMA_STAFF_KV.attackSpeed));
    }
}
