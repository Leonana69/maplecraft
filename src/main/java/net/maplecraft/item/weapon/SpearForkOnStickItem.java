package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSpearItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.SPEAR_FORK_ON_STICK_KV;

public class SpearForkOnStickItem extends WeaponSpearItem {
    public static String itemName = "spear_fork_on_stick";
    public SpearForkOnStickItem() {
        super(new EquipBaseData()
                .levelReq(SPEAR_FORK_ON_STICK_KV.levelReq)
                .addStat("ATTACK", SPEAR_FORK_ON_STICK_KV.attack)
                .addStat("ATTACK_SPEED", SPEAR_FORK_ON_STICK_KV.attackSpeed));
    }
}
