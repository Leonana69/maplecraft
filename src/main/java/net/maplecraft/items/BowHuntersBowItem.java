package net.maplecraft.items;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.*;
import net.minecraft.world.item.Item;

import java.util.List;

import static net.maplecraft.utils.PotentialType.getDefaultPotential;

public class BowHuntersBowItem extends BowWeaponItem {
    public BowHuntersBowItem() {
        super(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT).durability(100),
                new BonusStats(List.of("ATTACK"), List.of(2)));
    }
}
