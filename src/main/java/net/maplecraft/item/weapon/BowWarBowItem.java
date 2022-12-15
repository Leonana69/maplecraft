package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.item.WeaponBowItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.BOW_WAR_BOW_KV;

public class BowWarBowItem extends WeaponBowItem {
    public static String itemName = "bow_war_bow";
    public BowWarBowItem() {
        super(new EquipBaseData()
                .levelReq(BOW_WAR_BOW_KV.levelReq)
                .addStat("ATTACK", BOW_WAR_BOW_KV.attack)
                .addStat("ATTACK_SPEED", BOW_WAR_BOW_KV.attackSpeed));
    }
}
