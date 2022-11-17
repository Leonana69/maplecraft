package net.maplecraft.items;

import net.maplecraft.utils.*;

import java.util.List;

import static net.maplecraft.utils.PotentialType.getDefaultPotential;

public class ClawBronzeIgorItem extends ClawWeaponItem {
    public ClawBronzeIgorItem() {
        super(new Properties().durability(150),
                new BonusStats(List.of("ATTACK"), List.of(2)));
    }
}