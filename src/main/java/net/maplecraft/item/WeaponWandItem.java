package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.EquipCategory;

public class WeaponWandItem extends WeaponItem {
    public WeaponWandItem(EquipBaseData data) {
        super(data.category(EquipCategory.WAND));
    }
}
