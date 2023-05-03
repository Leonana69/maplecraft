package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponDaggerItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.DAGGER_RAZOR_KV;

public class DaggerRazorItem extends WeaponDaggerItem {
    public static String itemName = "dagger_razor";
    public DaggerRazorItem() {
        super(new EquipBaseData()
                .levelReq(DAGGER_RAZOR_KV.levelReq)
                .addStat("ATTACK", DAGGER_RAZOR_KV.attack)
                .addStat("ATTACK_SPEED", DAGGER_RAZOR_KV.attackSpeed));
    }
}
