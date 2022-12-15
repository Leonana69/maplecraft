package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.item.WeaponSpearItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.SPEAR_MAPLE_IMPALER_KV;

public class SpearMapleImpalerItem extends WeaponSpearItem {
    public static String itemName = "spear_maple_impaler";
    public SpearMapleImpalerItem() {
        super(new EquipBaseData()
                .levelReq(SPEAR_MAPLE_IMPALER_KV.levelReq)
                .addStat("ATTACK", SPEAR_MAPLE_IMPALER_KV.attack)
                .addStat("ATTACK_SPEED", SPEAR_MAPLE_IMPALER_KV.attackSpeed));
    }
}
