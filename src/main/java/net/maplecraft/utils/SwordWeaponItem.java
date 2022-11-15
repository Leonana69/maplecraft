package net.maplecraft.utils;

public class SwordWeaponItem extends BaseEquipItem {
    public float attackSpeed;

    public SwordWeaponItem(Properties itemProperties, BonusStats bs) {
        super(itemProperties, EquipCategory.SWORD, bs);
    }
}
