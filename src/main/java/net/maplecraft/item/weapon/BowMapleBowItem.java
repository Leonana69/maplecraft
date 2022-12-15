package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.item.WeaponBowItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.BOW_MAPLE_BOW_KV;

public class BowMapleBowItem extends WeaponBowItem {
    public static String itemName = "bow_maple_bow";
    public BowMapleBowItem() {
        super(new EquipBaseData()
                .levelReq(BOW_MAPLE_BOW_KV.levelReq)
                .addStat("ATTACK", BOW_MAPLE_BOW_KV.attack)
                .addStat("ATTACK_SPEED", BOW_MAPLE_BOW_KV.attackSpeed));
    }
}
