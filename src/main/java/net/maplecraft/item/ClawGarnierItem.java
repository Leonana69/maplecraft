package net.maplecraft.item;

import net.maplecraft.utils.*;
import net.minecraft.world.item.*;

public class ClawGarnierItem extends WeaponClawItem {
    public static String itemName = "claw_garnier";
    public ClawGarnierItem() {
        super(new EquipBaseData()
                .levelReq(0)
                .addStat("ATTACK", 1));
    }
}