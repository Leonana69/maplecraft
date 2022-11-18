package net.maplecraft.utils;

import java.util.List;
import java.util.Random;

public enum PotentialType {
    NONE("NONE", 0),
    MAX_HP("MAX HP", 1),
    ATTACK("ATTACK", 2),
    DEFENSE("DEFENSE", 3),
    STATS("STATS", 4),
    SPEED("SPEED", 5),
    JUMP("JUMP", 6);

    public static final List<PotentialType> VALUES = List.of(values());
    public static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    String typeName;
    int type;

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

    public static String getPotentialAsString(PotentialType pt, PotentialRarity rarity) {
        return pt.typeName + ": +" + rarity.type;
    }
}
