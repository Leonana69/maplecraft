package net.maplecraft.item;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.*;
import net.minecraft.world.item.Item;

public class BowHuntersBowItem extends WeaponBowItem {
    public BowHuntersBowItem() {
        super(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT).durability(100),
                new EquipBaseData()
                        .addStat("ATTACK", 3));
    }
}
