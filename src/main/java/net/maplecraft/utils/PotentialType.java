package net.maplecraft.utils;

import java.util.List;
import java.util.Random;

public enum PotentialType {
    NONE("NONE", 0),
    MAX_HP("MAX HP", 1),
    ATT("ATT", 2),
    MATT("M.ATT", 3),
    STATS("STATS", 4),
    SPEED("SPEED", 5),
    JUMP("JUMP", 6),
    DEFENSE("DEF", 7);

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
}
