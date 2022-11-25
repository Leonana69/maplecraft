package net.maplecraft.item;

import net.maplecraft.utils.*;
import net.minecraft.world.item.Item;

public class SwordStonetoothSwordItem extends WeaponSwordItem {
    public static String itemName = "sword_stonetooth_sword";
    public SwordStonetoothSwordItem() {
        super(new EquipBaseData()
                .levelReq(10)
                .addStat("ATTACK", 6)
                .addStat("ATTACK_SPEED", 2));
    }
}
