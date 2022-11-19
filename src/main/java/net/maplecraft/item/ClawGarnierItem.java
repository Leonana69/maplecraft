package net.maplecraft.item;

import net.maplecraft.utils.*;
import net.minecraft.world.item.*;

public class ClawGarnierItem extends ClawWeaponItem {
    public ClawGarnierItem() {
        super(new Item.Properties().durability(100),
                new EquipBaseData().addStat("ATTACK", 1));
    }
}