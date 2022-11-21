package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSpearItem;

public class SpearForkOnStickItem extends WeaponSpearItem {
    public static String itemName = "spear_fork_on_stick";
    public SpearForkOnStickItem() {
        super(new EquipBaseData()
                .levelReq(5)
                .addStat("ATTACK", 7)
                .addStat("ATTACK_SPEED", 4));
    }
}
