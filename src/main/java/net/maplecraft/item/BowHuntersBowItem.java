package net.maplecraft.item;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.*;
import net.minecraft.world.item.Item;

public class BowHuntersBowItem extends WeaponBowItem {
    public static String itemName = "bow_hunters_bow";
    public BowHuntersBowItem() {
        super(new EquipBaseData()
                .levelReq(5)
                .addStat("ATTACK", 3));
    }
}
