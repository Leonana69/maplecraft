package net.maplecraft.utils;

public class EquipBaseData {
    public final static int max_star_force = 5;
    public static int durationBase = 50;
    public static int durationPerLevel = 10;

    public EquipCategory category = EquipCategory.NONE;
    public BaseStats baseStats = new BaseStats();
    public int levelReq = 0;
    public int jobReq = 0;

    public EquipBaseData baseStats(BaseStats b) {
        baseStats = b;
        return this;
    }

    public EquipBaseData levelReq(int l) {
        levelReq = l;
        return this;
    }

    public EquipBaseData jobReq(int j) {
        jobReq = j;
        return this;
    }

    public EquipBaseData category(EquipCategory c) {
        category = c;
        return this;
    }

    public EquipBaseData addStat(String type, int value) {
        baseStats.add(type, value);
        return this;
    }
}
