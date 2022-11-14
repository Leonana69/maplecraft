package net.maplecraft.items;

import net.maplecraft.utils.BonusStats;
import net.maplecraft.utils.ClawWeaponItem;
import net.minecraft.world.item.*;

import java.util.List;

public class GarnierClawWeaponItem extends ClawWeaponItem {
    public GarnierClawWeaponItem() {
        super(new Item.Properties().durability(100),
                new BonusStats(List.of("ATTACK"), List.of(1)));
    }
}