package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponWandItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.WAND_WIZARD_STAFF_KV;

public class WandWizardStaffItem extends WeaponWandItem {
    public static String itemName = "wand_wizard_staff";
    public WandWizardStaffItem() {
        super(new EquipBaseData()
                .levelReq(WAND_WIZARD_STAFF_KV.levelReq)
                .addStat("M.ATTACK", WAND_WIZARD_STAFF_KV.attack)
                .addStat("ATTACK_SPEED", WAND_WIZARD_STAFF_KV.attackSpeed));
    }
}
