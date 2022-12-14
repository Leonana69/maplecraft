package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.item.WeaponBowItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.BOW_HUNTERS_BOW_KV;

public class BowHuntersBowItem extends WeaponBowItem {
    public static String itemName = "bow_hunters_bow";
    public BowHuntersBowItem() {
        super(new EquipBaseData()
                .levelReq(BOW_HUNTERS_BOW_KV.levelReq)
                .addStat("ATTACK", BOW_HUNTERS_BOW_KV.attack)
                .addStat("ATTACK_SPEED", BOW_HUNTERS_BOW_KV.attackSpeed));
    }
}
