package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponDaggerItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.DAGGER_STINGER_KV;

public class DaggerStingerItem extends WeaponDaggerItem {
    public static String itemName = "dagger_stinger";
    public DaggerStingerItem() {
        super(new EquipBaseData()
                .levelReq(DAGGER_STINGER_KV.levelReq)
                .addStat("ATTACK", DAGGER_STINGER_KV.attack)
                .addStat("ATTACK_SPEED", DAGGER_STINGER_KV.attackSpeed));
    }
}
