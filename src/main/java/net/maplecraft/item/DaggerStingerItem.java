package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponDaggerItem;

public class DaggerStingerItem extends WeaponDaggerItem {
    public static String itemName = "dagger_stinger";
    public DaggerStingerItem() {
        super(new EquipBaseData()
                .levelReq(5)
                .addStat("ATTACK", 3)
                .addStat("ATTACK_SPEED", 2));
    }
}
