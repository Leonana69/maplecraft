package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponClawItem;

public class ClawMapleKandayoItem extends WeaponClawItem {
    public ClawMapleKandayoItem() {
        super(new Properties().durability(150),
                new EquipBaseData()
                        .addStat("ATTACK", 4));
    }
}