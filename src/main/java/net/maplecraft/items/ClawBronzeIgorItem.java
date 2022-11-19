package net.maplecraft.items;

import net.maplecraft.utils.*;

import java.util.List;

public class ClawBronzeIgorItem extends ClawWeaponItem {
    public ClawBronzeIgorItem() {
        super(new Properties().durability(150),
                new BaseStats(List.of("ATTACK"), List.of(2)));
    }
}