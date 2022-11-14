package net.maplecraft.items;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.BonusStats;
import net.maplecraft.utils.BowWeaponItem;

import java.util.List;

public class MapleBowWeaponItem extends BowWeaponItem {
    public MapleBowWeaponItem() {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT).durability(120),
                new BonusStats(List.of("ATTACK"), List.of(3)));
    }
}
