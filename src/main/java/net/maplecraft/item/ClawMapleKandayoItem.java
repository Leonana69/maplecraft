package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponClawItem;

public class ClawMapleKandayoItem extends WeaponClawItem {
    public ClawMapleKandayoItem() {
        super(new EquipBaseData()
                .levelReq(15)
                .addStat("ATTACK", 4));
    }
}