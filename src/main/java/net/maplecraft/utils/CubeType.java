package net.maplecraft.utils;

public enum CubeType {
    OCCULT_CUBE(MapleRarity.EPIC, new float [] { 0.10F, 0.0F, 0.0F }),
    MASTER_CRAFTSMAN_CUBE(MapleRarity.UNIQUE, new float [] { 0.15F, 0.08F, 0.0F }),
    MEISTER_CUBE(MapleRarity.LEGENDARY, new float [] { 0.50F, 0.30F, 0.20F }),
    RED_CUBE(MapleRarity.LEGENDARY, new float [] { 0.20F, 0.14F, 0.08F }),
    BLACK_CUBE(MapleRarity.LEGENDARY, new float [] { 0.30F, 0.20F, 0.12F });

    final MapleRarity highest;
    final float [] chance;

    CubeType(MapleRarity r, float [] chance) {
        this.highest = r;
        this.chance = chance;
    }
}
