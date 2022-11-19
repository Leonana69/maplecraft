package net.maplecraft.item;

import net.maplecraft.utils.*;
import net.minecraft.world.item.Item;

public class SwordStonetoothSwordItem extends SwordWeaponItem {
    public SwordStonetoothSwordItem() {
        super(new Item.Properties().durability(100),
                new EquipBaseData().addStat("ATTACK", 4));
    }
}
