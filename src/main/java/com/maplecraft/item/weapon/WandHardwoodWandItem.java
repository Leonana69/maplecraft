package com.maplecraft.item.weapon;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.item.WeaponWandItem;

import static com.maplecraft.utils.AllWeaponEquipKeyValues.WAND_HARDWOOD_WAND_KV;

public class WandHardwoodWandItem extends WeaponWandItem {
    public static String itemName = "wand_hardwood_wand";
    public WandHardwoodWandItem() {
        super(new EquipBaseData()
                .levelReq(WAND_HARDWOOD_WAND_KV.levelReq)
                .addStat("M.ATTACK", WAND_HARDWOOD_WAND_KV.attack)
                .addStat("ATTACK_SPEED", WAND_HARDWOOD_WAND_KV.attackSpeed));
    }
}
