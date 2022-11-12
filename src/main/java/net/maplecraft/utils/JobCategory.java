package net.maplecraft.utils;

public enum JobCategory {
    NONE(0, "None"),
    WARRIOR(1, "Warrior"),
    MAGICIAN(2, "Magician"),
    BOWMAN(3, "Bowman"),
    THIEF(4, "Thief");

    public final int type;
    public final String typeName;

    JobCategory(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }
}
