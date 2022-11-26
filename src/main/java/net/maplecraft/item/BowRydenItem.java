package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponBowItem;

public class BowRydenItem extends WeaponBowItem {
    public static String itemName = "bow_ryden";
    public BowRydenItem() {
        super(new EquipBaseData()
                .levelReq(10)
                .addStat("ATTACK", 4));
    }
}
