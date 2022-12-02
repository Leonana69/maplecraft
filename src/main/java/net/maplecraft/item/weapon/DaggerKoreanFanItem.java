package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponDaggerItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.DAGGER_KOREAN_FAN_KV;

public class DaggerKoreanFanItem extends WeaponDaggerItem {
    public static String itemName = "dagger_korean_fan";
    public DaggerKoreanFanItem() {
        super(new EquipBaseData()
                .levelReq(DAGGER_KOREAN_FAN_KV.levelReq)
                .addStat("ATTACK", DAGGER_KOREAN_FAN_KV.attack)
                .addStat("ATTACK_SPEED", DAGGER_KOREAN_FAN_KV.attackSpeed));
    }
}
