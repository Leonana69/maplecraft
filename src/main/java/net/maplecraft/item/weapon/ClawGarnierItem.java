package net.maplecraft.item.weapon;

import net.maplecraft.item.WeaponClawItem;
import net.maplecraft.utils.*;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.CLAW_GARNIER_KV;

public class ClawGarnierItem extends WeaponClawItem {
    public static String itemName = "claw_garnier";
    public ClawGarnierItem() {
        super(new EquipBaseData()
                .levelReq(CLAW_GARNIER_KV.levelReq)
                .addStat("ATTACK", CLAW_GARNIER_KV.attack)
                .addStat("ATTACK_SPEED", CLAW_GARNIER_KV.attackSpeed));
    }
}