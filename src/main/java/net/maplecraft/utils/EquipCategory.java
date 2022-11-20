package net.maplecraft.utils;

public enum EquipCategory {
    NONE(-1, "NONE"),
    CLAW(0, "CLAW"),
    SWORD(0, "SWORD"),
    BOW(0, "BOW"),
    WAND(0, "WAND"),
    SPEAR(0, "SPEAR"),
    HAT(1, "HAT"),
    TOP(1, "TOP"),
    BOTTOM(1, "BOTTOM"),
    SHOES(1, "SHOES");

    // 0: weapon
    // 1: armor
    public final int type;
    public final String typeName;
    EquipCategory(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }
}
