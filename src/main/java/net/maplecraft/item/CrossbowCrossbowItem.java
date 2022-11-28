package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponCrossbowItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.CROSSBOW_CROSSBOW_KV;

public class CrossbowCrossbowItem extends WeaponCrossbowItem {
    public static String itemName = "crossbow_crossbow";
    public CrossbowCrossbowItem() {
        super(new EquipBaseData()
                .levelReq(CROSSBOW_CROSSBOW_KV.levelReq)
                .addStat("ATTACK", CROSSBOW_CROSSBOW_KV.attack)
                .addStat("ATTACK_SPEED", CROSSBOW_CROSSBOW_KV.attackSpeed));
    }
}
