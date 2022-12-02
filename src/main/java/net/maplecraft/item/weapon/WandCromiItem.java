package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponWandItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.WAND_CROMI_KV;

public class WandCromiItem extends WeaponWandItem {
    public static String itemName = "wand_cromi";
    public WandCromiItem() {
        super(new EquipBaseData()
                .levelReq(WAND_CROMI_KV.levelReq)
                .addStat("M.ATTACK", WAND_CROMI_KV.attack)
                .addStat("ATTACK_SPEED", WAND_CROMI_KV.attackSpeed));
    }
}
