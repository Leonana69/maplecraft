package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponClawItem;

public class ClawMapleKandayoItem extends WeaponClawItem {
    public static String itemName = "claw_maple_kandayo";
    public ClawMapleKandayoItem() {
        super(new EquipBaseData()
                .levelReq(15)
                .addStat("ATTACK", 4));
    }
}