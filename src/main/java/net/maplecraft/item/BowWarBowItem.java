package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponBowItem;

public class BowWarBowItem extends WeaponBowItem {
    public static String itemName = "bow_war_bow";
    public BowWarBowItem() {
        super(new EquipBaseData()
                .levelReq(0)
                .addStat("ATTACK", 2));
    }
}
