package net.maplecraft.item;

import net.maplecraft.utils.*;

public class ClawBronzeIgorItem extends WeaponClawItem {
    public static String itemName = "claw_bronze_igor";
    public ClawBronzeIgorItem() {
        super(new EquipBaseData()
                .levelReq(5)
                .addStat("ATTACK", 2));
    }
}