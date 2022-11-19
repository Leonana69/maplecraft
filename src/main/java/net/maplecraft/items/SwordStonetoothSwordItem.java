package net.maplecraft.items;

import net.maplecraft.utils.*;
import net.minecraft.world.item.Item;

import java.util.List;

public class SwordStonetoothSwordItem extends SwordWeaponItem {
    public SwordStonetoothSwordItem() {
        super(new Item.Properties().durability(100),
                new BaseStats(List.of("ATTACK"), List.of(4)));
    }
}
