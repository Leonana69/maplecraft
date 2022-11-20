package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSwordItem;

public class SwordMapleSwordItem extends WeaponSwordItem {
    public SwordMapleSwordItem() {
        super(new Properties().durability(150),
                new EquipBaseData()
                        .addStat("ATTACK", 7)
                        .addStat("ATTACK_SPEED", 2));
    }
}
