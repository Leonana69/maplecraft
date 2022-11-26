package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponDaggerItem;

public class DaggerKoreanFanItem extends WeaponDaggerItem {
    public static String itemName = "dagger_korean_fan";
    public DaggerKoreanFanItem() {
        super(new EquipBaseData()
                .levelReq(10)
                .addStat("ATTACK", 5)
                .addStat("ATTACK_SPEED", 2));
    }
}
