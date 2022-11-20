package net.maplecraft.item;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.*;

public class BowMapleBowItem extends BowWeaponItem {
    public BowMapleBowItem() {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT).durability(120),
                new EquipBaseData().addStat("ATTACK", 5));
    }
}
