package net.maplecraft.item;

import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSpearItem;
import net.minecraft.world.item.Item;

public class SpearForkOnStick extends WeaponSpearItem {
    public SpearForkOnStick() {
        super(new EquipBaseData()
                .levelReq(5)
                .addStat("ATTACK", 7)
                .addStat("ATTACK_SPEED", 4));
    }
}
