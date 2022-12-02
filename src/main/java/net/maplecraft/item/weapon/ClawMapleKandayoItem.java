package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponClawItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.CLAW_MAPLE_KANDAYO_KV;

public class ClawMapleKandayoItem extends WeaponClawItem {
    public static String itemName = "claw_maple_kandayo";
    public ClawMapleKandayoItem() {
        super(new EquipBaseData()
                .levelReq(CLAW_MAPLE_KANDAYO_KV.levelReq)
                .addStat("ATTACK", CLAW_MAPLE_KANDAYO_KV.attack)
                .addStat("ATTACK_SPEED", CLAW_MAPLE_KANDAYO_KV.attackSpeed));
    }
}