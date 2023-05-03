package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponWandItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.WAND_WIZARD_STAFF_KV;

public class WandWizardStaffItem extends WeaponWandItem {
    public static String itemName = "wand_wizard_staff";
    public WandWizardStaffItem() {
        super(new EquipBaseData()
                .levelReq(WAND_WIZARD_STAFF_KV.levelReq)
                .addStat("M.ATTACK", WAND_WIZARD_STAFF_KV.attack)
                .addStat("ATTACK_SPEED", WAND_WIZARD_STAFF_KV.attackSpeed));
    }
}
