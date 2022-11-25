package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponDaggerItem;

public class DaggerMapleWagnerItem extends WeaponDaggerItem {
    public static String itemName = "dagger_maple_wagner";
    public DaggerMapleWagnerItem() {
        super(new EquipBaseData()
                .levelReq(0)
                .addStat("ATTACK", 7)
                .addStat("ATTACK_SPEED", 2));
    }
}
