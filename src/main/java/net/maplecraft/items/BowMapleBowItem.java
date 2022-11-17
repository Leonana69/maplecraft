package net.maplecraft.items;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.*;

import java.util.List;

import static net.maplecraft.utils.PotentialType.getDefaultPotential;

public class BowMapleBowItem extends BowWeaponItem {
    public BowMapleBowItem() {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT).durability(120),
                new BonusStats(List.of("ATTACK"), List.of(3)));
    }
}
