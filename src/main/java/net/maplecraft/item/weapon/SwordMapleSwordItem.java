package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.item.WeaponSwordItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.SWORD_MAPLE_SWORD_KV;

public class SwordMapleSwordItem extends WeaponSwordItem {
    public static String itemName = "sword_maple_sword";
    public SwordMapleSwordItem() {
        super(new EquipBaseData()
                .levelReq(SWORD_MAPLE_SWORD_KV.levelReq)
                .addStat("ATTACK", SWORD_MAPLE_SWORD_KV.attack)
                .addStat("ATTACK_SPEED", SWORD_MAPLE_SWORD_KV.attackSpeed));
    }
}
