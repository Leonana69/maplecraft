package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponBowItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.BOW_RYDEN_KV;

public class BowRydenItem extends WeaponBowItem {
    public static String itemName = "bow_ryden";
    public BowRydenItem() {
        super(new EquipBaseData()
                .levelReq(BOW_RYDEN_KV.levelReq)
                .addStat("ATTACK", BOW_RYDEN_KV.attack)
                .addStat("ATTACK_SPEED", BOW_RYDEN_KV.attackSpeed));
    }
}
