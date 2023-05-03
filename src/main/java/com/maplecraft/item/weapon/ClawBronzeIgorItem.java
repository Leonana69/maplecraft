package com.maplecraft.item.weapon;

import com.maplecraft.item.WeaponClawItem;
import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.utils.*;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.CLAW_BRONZE_IGOR_KV;

public class ClawBronzeIgorItem extends WeaponClawItem {
    public static String itemName = "claw_bronze_igor";
    public ClawBronzeIgorItem() {
        super(new EquipBaseData()
                .levelReq(CLAW_BRONZE_IGOR_KV.levelReq)
                .addStat("ATTACK", CLAW_BRONZE_IGOR_KV.attack)
                .addStat("ATTACK_SPEED", CLAW_BRONZE_IGOR_KV.attackSpeed));
    }
}