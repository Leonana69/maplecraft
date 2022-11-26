package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponClawItem;

public class ClawBlackScarabItem extends WeaponClawItem {
    public static String itemName = "claw_black_scarab";
    public ClawBlackScarabItem() {
        super(new EquipBaseData()
                .levelReq(10)
                .addStat("ATTACK", 3));
    }
}