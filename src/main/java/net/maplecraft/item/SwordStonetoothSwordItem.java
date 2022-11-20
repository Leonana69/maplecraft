package net.maplecraft.item;

import net.maplecraft.utils.*;
import net.minecraft.world.item.Item;

public class SwordStonetoothSwordItem extends WeaponSwordItem {
    public SwordStonetoothSwordItem() {
        super(new Item.Properties().durability(100),
                new EquipBaseData()
                        .addStat("ATTACK", 5)
                        .addStat("ATTACK_SPEED", 2));
    }
}
