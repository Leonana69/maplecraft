package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.item.WeaponCrossbowItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.CROSSBOW_GOLDEN_RAVEN_KV;

public class CrossbowGoldenRavenItem extends WeaponCrossbowItem {
    public static String itemName = "crossbow_golden_raven";
    public CrossbowGoldenRavenItem() {
        super(new EquipBaseData()
                .levelReq(CROSSBOW_GOLDEN_RAVEN_KV.levelReq)
                .addStat("ATTACK", CROSSBOW_GOLDEN_RAVEN_KV.attack)
                .addStat("ATTACK_SPEED", CROSSBOW_GOLDEN_RAVEN_KV.attackSpeed));
    }
}
