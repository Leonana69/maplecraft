package net.maplecraft.utils;

//public int bonus_hp_percent;
//public int bonus_attack_percent;
//public int bonus_stats_percent;
//public int bonus_jump_percent;
//public int bonus_speed_percent;

import java.util.List;
import java.util.Random;

public enum PotentialType {
    NONE(0, 0, 0, 0, 0, 0),

    MAX_HP_1(1, 0, 0, 0, 0, 0),
    MAX_HP_2(2, 0, 0, 0, 0, 0),
    MAX_HP_3(3, 0, 0, 0, 0, 0),
    MAX_HP_4(4, 0, 0, 0, 0, 0),

    ATTACK_1(0, 1, 0, 0, 0, 0),
    ATTACK_2(0, 2, 0, 0, 0, 0),
    ATTACK_3(0, 3, 0, 0, 0, 0),
    ATTACK_4(0, 4, 0, 0, 0, 0),

    ARMOR_1(0, 0, 1, 0, 0, 0),
    ARMOR_2(0, 0, 2, 0, 0, 0),
    ARMOR_3(0, 0, 3, 0, 0, 0),
    ARMOR_4(0, 0, 4, 0, 0, 0),

    STATS_1(0, 0, 0, 1, 0, 0),
    STATS_2(0, 0, 0, 2, 0, 0),
    STATS_3(0, 0, 0, 3, 0, 0),
    STATS_4(0, 0, 0, 4, 0, 0),

    SPEED_1(0, 0, 0, 0, 1, 0),
    SPEED_2(0, 0, 0, 0, 2, 0),
    SPEED_3(0, 0, 0, 0, 3, 0),
    SPEED_4(0, 0, 0, 0, 4, 0),

    JUMP_1(0, 0, 0, 0, 0, 1),
    JUMP_2(0, 0, 0, 0, 0, 2),
    JUMP_3(0, 0, 0, 0, 0, 3),
    JUMP_4(0, 0, 0, 0, 0, 4);


    private static final int valuesType = 4;
    private static final List<PotentialType> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final int statsTypes = SIZE / valuesType;
    private static final Random RANDOM = new Random();
    public final BonusStats potentialValue;
    PotentialType(int b_max_hp, int b_attack, int b_armor, int b_stats, int b_speed, int b_jump) {
        potentialValue = new BonusStats(b_max_hp, b_attack, b_armor, b_stats, b_speed, b_jump);
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
        for (int i = 0; i < potentialValue.values.size(); i++) {
            int value = potentialValue.values.get(i);
            if (value > 0) {
                return BonusStats.valuesName.get(i) + ": +" + value;
            }
        }
        return null;
    }
}
