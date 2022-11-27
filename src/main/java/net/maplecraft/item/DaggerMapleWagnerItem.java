package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponDaggerItem;

import static net.maplecraft.utils.AllWeaponEquipKeyValues.DAGGER_MAPLE_WAGNER_KV;

public class DaggerMapleWagnerItem extends WeaponDaggerItem {
    public static String itemName = "dagger_maple_wagner";
    public DaggerMapleWagnerItem() {
        super(new EquipBaseData()
                .levelReq(DAGGER_MAPLE_WAGNER_KV.levelReq)
                .addStat("ATTACK", DAGGER_MAPLE_WAGNER_KV.attack)
                .addStat("ATTACK_SPEED", DAGGER_MAPLE_WAGNER_KV.attackSpeed));
    }
}
