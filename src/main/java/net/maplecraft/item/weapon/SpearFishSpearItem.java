package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSpearItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.SPEAR_FISH_SPEAR_KV;

public class SpearFishSpearItem extends WeaponSpearItem {
    public static String itemName = "spear_fish_spear";
    public SpearFishSpearItem() {
        super(new EquipBaseData()
                .levelReq(SPEAR_FISH_SPEAR_KV.levelReq)
                .addStat("ATTACK", SPEAR_FISH_SPEAR_KV.attack)
                .addStat("ATTACK_SPEED", SPEAR_FISH_SPEAR_KV.attackSpeed));
    }
}
