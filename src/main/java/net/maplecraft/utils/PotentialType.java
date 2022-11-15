package net.maplecraft.utils;

import java.util.List;
import java.util.Random;

public enum PotentialType {
    NONE("NONE", 0),

    MAX_HP_1("MAX_HP", 1),
    MAX_HP_2("MAX_HP", 2),
    MAX_HP_3("MAX_HP", 3),
    MAX_HP_4("MAX_HP", 4),

    ATTACK_1("ATTACK", 1),
    ATTACK_2("ATTACK", 2),
    ATTACK_3("ATTACK", 3),
    ATTACK_4("ATTACK", 4),

    DEFENSE_1("DEFENSE", 1),
    DEFENSE_2("DEFENSE", 2),
    DEFENSE_3("DEFENSE", 3),
    DEFENSE_4("DEFENSE", 4),

    STATS_1("STATS", 1),
    STATS_2("STATS", 2),
    STATS_3("STATS", 3),
    STATS_4("STATS", 4),

    SPEED_1("SPEED", 1),
    SPEED_2("SPEED", 2),
    SPEED_3("SPEED", 3),
    SPEED_4("SPEED", 4),

    JUMP_1("JUMP", 1),
    JUMP_2("JUMP", 2),
    JUMP_3("JUMP", 3),
    JUMP_4("JUMP", 4);


    private static final int valueTypes = 4;
    private static final List<PotentialType> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final int statsTypes = SIZE / valueTypes;
    private static final Random RANDOM = new Random();
    public final BonusStats potentialValue;

    PotentialType(String type, int value) {
        potentialValue = new BonusStats(type, value);
    }

//    public PotentialType getRandomPotential(EquipCategory ec, PotentialRarity pr) {
//        if (pr.type == 0)
//            return NONE;
//        else {
//            return VALUES.get(RANDOM.nextInt(statsTypes) * valuesType + pr.type);
//        }
//    }

    @Override
    public String toString() {
        for (int i = 0; i < potentialValue.valueTypes; i++) {
            int value = potentialValue.values[i];
            if (value > 0) {
                return BonusStats.valuesName.get(i) + ": +" + value;
            }
        }
        return "null";
    }
}
