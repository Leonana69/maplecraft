package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponClawItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.CLAW_BLACK_SCARAB_KV;

public class ClawBlackScarabItem extends WeaponClawItem {
    public static String itemName = "claw_black_scarab";
    public ClawBlackScarabItem() {
        super(new EquipBaseData()
                .levelReq(CLAW_BLACK_SCARAB_KV.levelReq)
                .addStat("ATTACK", CLAW_BLACK_SCARAB_KV.attack)
                .addStat("ATTACK_SPEED", CLAW_BLACK_SCARAB_KV.attackSpeed));
    }
}