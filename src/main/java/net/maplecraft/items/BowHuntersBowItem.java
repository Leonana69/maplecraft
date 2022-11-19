package net.maplecraft.items;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.*;
import net.minecraft.world.item.Item;

import java.util.List;

public class BowHuntersBowItem extends BowWeaponItem {
    public BowHuntersBowItem() {
        super(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT).durability(100),
                new BaseStats(List.of("ATTACK"), List.of(2)));
    }
}
