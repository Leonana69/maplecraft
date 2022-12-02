package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponWandItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.WAND_MAPLE_LAMA_STAFF_KV;

public class WandMapleLamaStaffItem extends WeaponWandItem {
    public static String itemName = "wand_maple_lama_staff";
    public WandMapleLamaStaffItem() {
        super(new EquipBaseData()
                .levelReq(WAND_MAPLE_LAMA_STAFF_KV.levelReq)
                .addStat("M.ATTACK", WAND_MAPLE_LAMA_STAFF_KV.attack)
                .addStat("ATTACK_SPEED", WAND_MAPLE_LAMA_STAFF_KV.attackSpeed));
    }
}
