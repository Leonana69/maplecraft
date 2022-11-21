package net.maplecraft.utils;

public enum JobCategory {
    NONE(0, 0, "None"),
    WARRIOR(1, 1, "WARRIOR"),
    MAGICIAN(2, 1, "MAGICIAN"),
    BOWMAN(3, 1, "BOWMAN"),
    THIEF(4, 1, "THIEF"),
    BISHOP(21, 2, "BISHOP");

    public final int type;
    public final int advancement;
    public final String typeName;

    JobCategory(int type, int advancement, String typeName) {
        this.type = type;
        this.advancement = advancement;
        this.typeName = typeName;
    }
}
