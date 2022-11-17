package net.maplecraft.items;

import net.maplecraft.utils.*;
import net.minecraft.world.item.Item;

import java.util.List;

import static net.maplecraft.utils.PotentialType.getDefaultPotential;

public class SwordStonetoothSwordItem extends SwordWeaponItem {
    public SwordStonetoothSwordItem() {
        super(new Item.Properties().durability(100),
                new BonusStats(List.of("ATTACK"), List.of(4)));
    }
}
