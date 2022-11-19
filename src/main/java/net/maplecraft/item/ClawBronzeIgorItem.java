package net.maplecraft.item;

import net.maplecraft.utils.*;

public class ClawBronzeIgorItem extends ClawWeaponItem {
    public ClawBronzeIgorItem() {
        super(new Properties().durability(150),
                new EquipBaseData().addStat("ATTACK", 2));
    }
}