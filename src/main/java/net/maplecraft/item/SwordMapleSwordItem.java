package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.SwordWeaponItem;

public class SwordMapleSwordItem extends SwordWeaponItem {
    public SwordMapleSwordItem() {
        super(new Properties().durability(100),
                new EquipBaseData().addStat("ATTACK", 7));
    }
}
