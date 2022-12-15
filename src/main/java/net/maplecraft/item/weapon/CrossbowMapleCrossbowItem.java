package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.item.WeaponCrossbowItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.CROSSBOW_MAPLE_CROSSBOW_KV;

public class CrossbowMapleCrossbowItem extends WeaponCrossbowItem {
    public static String itemName = "crossbow_maple_crossbow";
    public CrossbowMapleCrossbowItem() {
        super(new EquipBaseData()
                .levelReq(CROSSBOW_MAPLE_CROSSBOW_KV.levelReq)
                .addStat("ATTACK", CROSSBOW_MAPLE_CROSSBOW_KV.attack)
                .addStat("ATTACK_SPEED", CROSSBOW_MAPLE_CROSSBOW_KV.attackSpeed));
    }
}
