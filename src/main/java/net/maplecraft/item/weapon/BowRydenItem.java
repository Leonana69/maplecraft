package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponBowItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.BOW_RYDEN_KV;

public class BowRydenItem extends WeaponBowItem {
    public static String itemName = "bow_ryden";
    public BowRydenItem() {
        super(new EquipBaseData()
                .levelReq(BOW_RYDEN_KV.levelReq)
                .addStat("ATTACK", BOW_RYDEN_KV.attack)
                .addStat("ATTACK_SPEED", BOW_RYDEN_KV.attackSpeed));
    }
}
