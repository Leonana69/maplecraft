package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponClawItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.CLAW_MAPLE_KANDAYO_KV;

public class ClawMapleKandayoItem extends WeaponClawItem {
    public static String itemName = "claw_maple_kandayo";
    public ClawMapleKandayoItem() {
        super(new EquipBaseData()
                .levelReq(CLAW_MAPLE_KANDAYO_KV.levelReq)
                .addStat("ATTACK", CLAW_MAPLE_KANDAYO_KV.attack)
                .addStat("ATTACK_SPEED", CLAW_MAPLE_KANDAYO_KV.attackSpeed));
    }
}