package net.maplecraft.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class BonusStats {
    public static int valueTypes = 6;
    public static List<String> valuesName = List.of(
            "Max HP", "ATTACK", "DEF", "STATS",
            "SPEED", "JUMP");

    public int[] values = new int[valueTypes];

    public BonusStats() {}
    public BonusStats(int [] values) {
        this.values = values;
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

    public String toString() {
        return Arrays.toString(values);
    }

    public static BonusStats fromString(String str) {
        String[] string = str.replaceAll("\\[", "")
                .replaceAll("]", "")
                .replaceAll(" ", "")
                .split(",");
        int [] values = Arrays.stream(string).mapToInt(Integer::parseInt).toArray();
        assert values.length == valueTypes;
        return new BonusStats(values);
    }

    public static BonusStats sum(List<BonusStats> list) {
        int [] values = new int[valueTypes];
        list.forEach( b -> {
            for (int i = 0; i < valueTypes; i++) {
                values[i] += b.values[i];
            }
        });
        return new BonusStats(values);
    }
}
