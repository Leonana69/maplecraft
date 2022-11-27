package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponDaggerItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.DAGGER_STINGER_KV;

public class DaggerStingerItem extends WeaponDaggerItem {
    public static String itemName = "dagger_stinger";
    public DaggerStingerItem() {
        super(new EquipBaseData()
                .levelReq(DAGGER_STINGER_KV.levelReq)
                .addStat("ATTACK", DAGGER_STINGER_KV.attack)
                .addStat("ATTACK_SPEED", DAGGER_STINGER_KV.attackSpeed));
    }
}
