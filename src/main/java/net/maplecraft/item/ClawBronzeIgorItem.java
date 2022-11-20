package net.maplecraft.item;

import net.maplecraft.utils.*;

public class ClawBronzeIgorItem extends WeaponClawItem {
    public ClawBronzeIgorItem() {
        super(new Properties().durability(120),
                new EquipBaseData()
                        .addStat("ATTACK", 2));
    }
}