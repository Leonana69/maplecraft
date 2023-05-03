package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponClawItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.CLAW_BLACK_SCARAB_KV;

public class ClawBlackScarabItem extends WeaponClawItem {
    public static String itemName = "claw_black_scarab";
    public ClawBlackScarabItem() {
        super(new EquipBaseData()
                .levelReq(CLAW_BLACK_SCARAB_KV.levelReq)
                .addStat("ATTACK", CLAW_BLACK_SCARAB_KV.attack)
                .addStat("ATTACK_SPEED", CLAW_BLACK_SCARAB_KV.attackSpeed));
    }
}