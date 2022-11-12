package net.maplecraft.utils;

import java.util.Arrays;
import java.util.List;

public class BonusStats {
//    public int max_hp;
//    public int attack;
//    public int armor;
//    public int stats;
//    public int speed;
//    public int jump;
    public List<Integer> values = Arrays.asList(0, 0, 0, 0, 0, 0);
    public final static List<String> valuesName = Arrays.asList("Max HP", "ATTACK", "ARMOR", "STATS", "SPEED", "JUMP");

    public BonusStats(int b_max_hp, int b_attack, int b_armor, int b_stats, int b_speed, int b_jump) {
        values.set(0, b_max_hp);
        values.set(1, b_attack);
        values.set(2, b_armor);
        values.set(3, b_stats);
        values.set(4, b_speed);
        values.set(5, b_jump);
    }

    public BonusStats() {

    }
}
