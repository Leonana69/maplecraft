package net.maplecraft.item;

import net.maplecraft.utils.*;
import net.minecraft.world.item.*;

public class ClawGarnierItem extends WeaponClawItem {
    public ClawGarnierItem() {
        super(new EquipBaseData()
                .levelReq(0)
                .addStat("ATTACK", 1));
    }
}