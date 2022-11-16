package net.maplecraft.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class BonusStats {
    // why not vector of int
    public final int valueTypes = 6;
    public int[] values = new int[valueTypes];
    public final static List<String> valuesName = List.of("Max_HP", "ATTACK", "DEFENSE", "STATS", "SPEED", "JUMP");

    public BonusStats() {
    }

    public BonusStats(List<String> type, List<Integer> value) {
        assert type.size() == value.size();
        for (int i = 0; i < type.size(); i++)
            this.set(type.get(i), value.get(i));
    }

    public BonusStats(String type, int value) {
        this.set(type, value);
    }

    public void set(String type, int value) {
        int index = valuesName.indexOf(type);
        if (index != -1)
            values[index] = value;
    }

    public int get(String type) {
        assert valuesName.contains(type);
        return values[valuesName.indexOf(type)];
    }
}
