package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponBowItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.BOW_HUNTERS_BOW_KV;

public class BowHuntersBowItem extends WeaponBowItem {
    public static String itemName = "bow_hunters_bow";
    public BowHuntersBowItem() {
        super(new EquipBaseData()
                .levelReq(BOW_HUNTERS_BOW_KV.levelReq)
                .addStat("ATTACK", BOW_HUNTERS_BOW_KV.attack)
                .addStat("ATTACK_SPEED", BOW_HUNTERS_BOW_KV.attackSpeed));
    }
}
