package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponCrossbowItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.CROSSBOW_BALANCHE_KV;

public class CrossbowBalancheItem extends WeaponCrossbowItem {
    public static String itemName = "crossbow_balanche";
    public CrossbowBalancheItem() {
        super(new EquipBaseData()
                .levelReq(CROSSBOW_BALANCHE_KV.levelReq)
                .addStat("ATTACK", CROSSBOW_BALANCHE_KV.attack)
                .addStat("ATTACK_SPEED", CROSSBOW_BALANCHE_KV.attackSpeed));
    }
}
