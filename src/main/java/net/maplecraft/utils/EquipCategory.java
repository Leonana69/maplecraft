package net.maplecraft.utils;

public enum EquipCategory {
    NONE(-1, "None"),
    CLAW(0, "Claw"),
    SWORD(0, "Sword"),
    BOW(0, "Bow"),
    WAND(0, "Wand"),
    HELMET(1, "Helmet"),
    CHEST_PLATE(1, "Chest plate"),
    LEGGINGS(1, "Leggings"),
    BOOTS(1, "Boots");

    // 0: weapon
    // 1: armor
    public final int type;
    public final String typeName;
    EquipCategory(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }
}
