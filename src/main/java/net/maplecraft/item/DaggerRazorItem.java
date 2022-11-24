package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponDaggerItem;

public class DaggerRazorItem extends WeaponDaggerItem {
    public static String itemName = "dagger_razor";
    public DaggerRazorItem() {
        super(new EquipBaseData()
                .levelReq(0)
                .addStat("ATTACK", 2)
                .addStat("ATTACK_SPEED", 2));
    }
}
