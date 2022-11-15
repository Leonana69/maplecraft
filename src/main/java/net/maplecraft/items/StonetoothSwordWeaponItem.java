package net.maplecraft.items;

import net.maplecraft.utils.BonusStats;
import net.maplecraft.utils.SwordWeaponItem;
import net.minecraft.world.item.Item;

import java.util.List;

public class StonetoothSwordWeaponItem extends SwordWeaponItem {
    public StonetoothSwordWeaponItem() {
        super(new Item.Properties().durability(100),
                new BonusStats(List.of("ATTACK"), List.of(4)));
    }
}
