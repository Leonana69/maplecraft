package net.maplecraft.items;

import net.maplecraft.utils.BonusStats;
import net.maplecraft.utils.ClawWeaponItem;

import java.util.List;

public class BronzeIgorClawWeaponItem extends ClawWeaponItem {
    public BronzeIgorClawWeaponItem() {
        super(new Properties().durability(150),
                new BonusStats(List.of("ATTACK"), List.of(2)));
    }
}