package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponWandItem;

public class WandHardwoodWandItem extends WeaponWandItem {
    public static String itemName = "wand_hardwood_wand";
    public WandHardwoodWandItem() {
        super(new EquipBaseData()
                .levelReq(0)
                .addStat("ATTACK", 2)
                .addStat("ATTACK_SPEED", 2));
    }
}
