package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponWandItem;

public class WandCromiItem extends WeaponWandItem {
    public static String itemName = "wand_cromi";
    public WandCromiItem() {
        super(new EquipBaseData()
                .levelReq(10)
                .addStat("M.ATTACK", 6)
                .addStat("ATTACK_SPEED", 2));
    }
}
