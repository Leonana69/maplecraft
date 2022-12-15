package net.maplecraft.utils;

import java.util.List;
import java.util.Random;

public enum PotentialType {
    NONE("NONE", 0, 0, false),
    MAX_HP("MAX HP", 1, 1, false),
    ATT("ATT", 2, 3, true),
    MATT("M.ATT", 3, 3, true),
    STATS("STATS", 4, 3, true),
    SPEED("SPEED", 5, 3, true),
    JUMP("JUMP", 6, 3, true),
    DEFENSE("DEF", 7, 1, true);

    public static final List<PotentialType> VALUES = List.of(values());
    public static final List<int []> possibleValues = List.of(
                    new int[] { 2, 3, 4, 5, 6, 7 },
                    new int[] { 1, 4, 5, 6, 7 },
                    new int[] { 1, 4, 5, 6 }
            );
    public static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    final String typeName;
    final int type;
    final int amplifier;
    final boolean isPercent;

    PotentialType(String typeName, int type, int amplifier, boolean isPercent) {
        this.typeName = typeName;
        this.type = type;
        this.amplifier = amplifier;
        this.isPercent = isPercent;
    }

    public static PotentialType getRandomPotentialType(EquipCategory ec, int rarity) {
        if (rarity == 0 || ec.type == -1)
            return NONE;

        int [] candidates = possibleValues.get(ec.type);
        return VALUES.get(candidates[RANDOM.nextInt(candidates.length)]);
    }
}
