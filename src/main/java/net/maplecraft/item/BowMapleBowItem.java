package net.maplecraft.item;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.*;

public class BowMapleBowItem extends WeaponBowItem {
    public BowMapleBowItem() {
        super(new EquipBaseData()
                .levelReq(15)
                .addStat("ATTACK", 6));
    }
}
