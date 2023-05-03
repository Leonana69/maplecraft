package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponBowItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.BOW_MAPLE_BOW_KV;

public class BowMapleBowItem extends WeaponBowItem {
    public static String itemName = "bow_maple_bow";
    public BowMapleBowItem() {
        super(new EquipBaseData()
                .levelReq(BOW_MAPLE_BOW_KV.levelReq)
                .addStat("ATTACK", BOW_MAPLE_BOW_KV.attack)
                .addStat("ATTACK_SPEED", BOW_MAPLE_BOW_KV.attackSpeed));
    }
}
