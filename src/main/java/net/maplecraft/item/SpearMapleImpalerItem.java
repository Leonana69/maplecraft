package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSpearItem;

public class SpearMapleImpalerItem extends WeaponSpearItem {
    public static String itemName = "spear_maple_impaler";
    public SpearMapleImpalerItem() {
        super(new EquipBaseData()
                .levelReq(15)
                .addStat("ATTACK", 9)
                .addStat("ATTACK_SPEED", 4));
    }
}
