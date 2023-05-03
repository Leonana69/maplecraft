package com.maplecraft.utils;

public enum AllArmorEquipKeyValues {
    HAT_RED_HEADBAND_KV(0, 1, 0, 0, 1, 0),
    HAT_WIZET_HAT_KV(0, 20, 10, 10, 10, 10),
    HAT_ZAKUM_HELMET_KV(15, 10, 5, 5, 5, 5),

    SHOES_RED_RUBBER_BOOTS_KV(0, 1, 0, 0, 1, 1),

    TOP_ORANGE_SPORTS_TSHIRT_KV(0, 2, 0, 0, 0, 0),
    BOTTOM_BLUE_JEAN_SHORTS_KV(0, 2, 0, 0, 0, 0);

    public final int levelReq;
    public final int armor;
    public final int maxHP;
    public final int stats;
    public final int speed;
    public final int jump;
    AllArmorEquipKeyValues(int levelReq, int armor, int maxHP, int stats, int speed, int jump) {
        this.levelReq = levelReq;
        this.armor = armor;
        this.maxHP = maxHP;
        this.stats = stats;
        this.speed = speed;
        this.jump = jump;
    }
}
