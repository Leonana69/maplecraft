package net.maplecraft.items;

import net.maplecraft.utils.BonusStats;
import net.maplecraft.utils.ClawWeaponItem;
import net.minecraft.world.item.*;

public class GarnierClawWeaponItem extends ClawWeaponItem {
    public GarnierClawWeaponItem() {
        super(new Item.Properties().durability(100),
                new BonusStats(0, 1, 0, 0, 0, 0));
    }
}