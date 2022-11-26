package net.maplecraft.item;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.*;

public class BowMapleBowItem extends WeaponBowItem {
    public static String itemName = "bow_maple_bow";
    public BowMapleBowItem() {
        super(new EquipBaseData()
                .levelReq(15)
                .addStat("ATTACK", 5));
    }
}
