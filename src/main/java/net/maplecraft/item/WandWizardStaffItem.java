package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponWandItem;

public class WandWizardStaffItem extends WeaponWandItem {
    public static String itemName = "wand_wizard_staff";
    public WandWizardStaffItem() {
        super(new EquipBaseData()
                .levelReq(5)
                .addStat("M.ATTACK", 4)
                .addStat("ATTACK_SPEED", 2));
    }
}
