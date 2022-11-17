package net.maplecraft.items;

import net.maplecraft.utils.*;
import net.minecraft.world.item.*;

import java.util.List;

import static net.maplecraft.utils.PotentialType.getDefaultPotential;

public class ClawGarnierItem extends ClawWeaponItem {
    public ClawGarnierItem() {
        super(new Item.Properties().durability(100),
                new BonusStats(List.of("ATTACK"), List.of(1)));
    }
}