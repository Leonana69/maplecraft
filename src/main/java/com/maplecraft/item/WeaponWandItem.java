package com.maplecraft.item;

import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.utils.EquipCategory;

public class WeaponWandItem extends WeaponItem {
    public WeaponWandItem(EquipBaseData data) {
        super(data.category(EquipCategory.WAND));
    }
}
