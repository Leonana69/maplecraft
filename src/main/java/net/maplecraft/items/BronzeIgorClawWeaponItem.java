package net.maplecraft.items;

import net.maplecraft.utils.BonusStats;
import net.maplecraft.utils.ClawWeaponItem;
import net.maplecraft.utils.PotentialRarity;

public class BronzeIgorClawWeaponItem extends ClawWeaponItem {
    public BronzeIgorClawWeaponItem() {
        super(new Properties().durability(100),
                new BonusStats(0, 2, 0, 0, 0, 0));
    }
}