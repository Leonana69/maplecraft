package net.maplecraft.item;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSpearItem;
import net.minecraft.world.item.Item;

public class SpearForkOnStick extends WeaponSpearItem {
    public SpearForkOnStick() {
        super(new Item.Properties().durability(150),
                new EquipBaseData()
                        .addStat("ATTACK", 7)
                        .addStat("ATTACK_SPEED", 4));
    }
}
