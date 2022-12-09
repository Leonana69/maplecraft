package net.maplecraft.utils;

public enum ScrollType {
    RARE_POTENTIAL_SCROLL(MapleRarity.RARE, 0.8F),
    EPIC_POTENTIAL_SCROLL(MapleRarity.EPIC, 0.7F),
    UNIQUE_POTENTIAL_SCROLL(MapleRarity.UNIQUE, 0.6F),
    LEGENDARY_POTENTIAL_SCROLL(MapleRarity.LEGENDARY, 0.5F);

    final MapleRarity highest;
    final float chance;

    ScrollType(MapleRarity r, float chance) {
        this.highest = r;
        this.chance = chance;
    }
}
