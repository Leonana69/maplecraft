package net.maplecraft.items;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.BonusStats;
import net.maplecraft.utils.BowWeaponItem;
import net.minecraft.world.item.Item;

import java.util.List;

public class HuntersBowWeaponItem extends BowWeaponItem {
    public HuntersBowWeaponItem() {
        super(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT).durability(100),
                new BonusStats(List.of("ATTACK"), List.of(2)));
    }
}
