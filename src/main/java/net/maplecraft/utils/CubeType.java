package net.maplecraft.utils;

public enum CubeType {
    OCCULT_CUBE(PotentialRarity.EPIC, new float [] { 0.10F, 0.0F, 0.0F }),
    MASTER_CRAFTSMAN_CUBE(PotentialRarity.UNIQUE, new float [] { 0.15F, 0.08F, 0.0F }),
    MEISTER_CUBE(PotentialRarity.LEGENDARY, new float [] { 0.50F, 0.30F, 0.20F }),
    RED_CUBE(PotentialRarity.LEGENDARY, new float [] { 0.20F, 0.14F, 0.08F }),
    BLACK_CUBE(PotentialRarity.LEGENDARY, new float [] { 0.30F, 0.20F, 0.12F });

    final PotentialRarity highest;
    final float [] chance;

    CubeType(PotentialRarity r, float [] chance) {
        this.highest = r;
        this.chance = chance;
    }
}
