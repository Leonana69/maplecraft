package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponWandItem;

public class WandMapleLamaStaffItem extends WeaponWandItem {
    public static String itemName = "wand_maple_lama_staff";
    public WandMapleLamaStaffItem() {
        super(new EquipBaseData()
                .levelReq(15)
                .addStat("M.ATTACK", 8)
                .addStat("ATTACK_SPEED", 2));
    }
}
