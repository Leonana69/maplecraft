package net.maplecraft.item.weapon;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponDaggerItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.DAGGER_RAZOR_KV;

public class DaggerRazorItem extends WeaponDaggerItem {
    public static String itemName = "dagger_razor";
    public DaggerRazorItem() {
        super(new EquipBaseData()
                .levelReq(DAGGER_RAZOR_KV.levelReq)
                .addStat("ATTACK", DAGGER_RAZOR_KV.attack)
                .addStat("ATTACK_SPEED", DAGGER_RAZOR_KV.attackSpeed));
    }
}
