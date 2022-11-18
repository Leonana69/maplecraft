package net.maplecraft.utils;

public enum ScrollType {
    RARE_POTENTIAL_SCROLL(PotentialRarity.RARE, 0.8F),
    EPIC_POTENTIAL_SCROLL(PotentialRarity.EPIC, 0.6F),
    UNIQUE_POTENTIAL_SCROLL(PotentialRarity.UNIQUE, 0.4F),
    LEGENDARY_POTENTIAL_SCROLL(PotentialRarity.LEGENDARY, 0.2F);

    final PotentialRarity highest;
    final float chance;

    ScrollType(PotentialRarity r, float chance) {
        this.highest = r;
        this.chance = chance;
    }
}
