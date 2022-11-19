package net.maplecraft.utils;

import java.util.List;
import java.util.Random;

public enum PotentialType {
    NONE("NONE", 0),
    MAX_HP(BonusStats.valuesName.get(0), 1),
    ATTACK(BonusStats.valuesName.get(1), 2),
    ARMOR(BonusStats.valuesName.get(2), 3),
    STATS(BonusStats.valuesName.get(3), 4),
    SPEED(BonusStats.valuesName.get(4), 5),
    JUMP(BonusStats.valuesName.get(5), 6);

    public static final List<PotentialType> VALUES = List.of(values());
    public static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    final String typeName;
    final int type;

    PotentialType(String typeName, int type) {
        this.typeName = typeName;
        this.type = type;
    }

    public static PotentialType getRandomPotentialType(EquipCategory ec, int rarity) {
        if (rarity == 0)
            return NONE;
        return VALUES.get(RANDOM.nextInt(SIZE - 1) + 1);
    }

    public static PotentialType [] getDefaultPotential() {
        return new PotentialType[] { NONE, NONE, NONE };
    }

    // TODO: refine the value
    public static String getPotentialAsString(PotentialType pt, MapleRarity rarity) {
        return pt == NONE ? "" : pt.typeName + ": +" + rarity.type;
    }

    public static BonusStats getPotentialAsBonusStats(PotentialType pt, MapleRarity rarity) {
        return new BonusStats(pt.typeName, rarity.type);
    }
}
