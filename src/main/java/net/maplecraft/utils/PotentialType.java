package net.maplecraft.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum PotentialType {
    NONE("NONE", 0),
    MAX_HP("MAX HP", 1),
    ATTACK("ATTACK", 2),
    STATS("STATS", 3),
    SPEED("SPEED", 4),
    JUMP("JUMP", 5),
    DEFENSE("DEF", 6);

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
