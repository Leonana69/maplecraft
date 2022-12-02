package net.maplecraft.utils;

import java.util.*;

public class BaseStats {
    public static int valueTypes = 8;
    public static List<String> valuesName = List.of(
            "ATTACK_SPEED", "ARMOR", "MAX HP", "ATTACK", "STATS",
            "SPEED", "JUMP", "M.ATTACK");

    public static List<String> suffix = List.of(
            "", "", "", "", "%", "%", "%", "");

    public int[] values = new int[valueTypes];

    public BaseStats() {}
    public BaseStats(int [] values) {
        this.values = values;
    }

    public BaseStats add(String type, int value) {
        this.set(type, value);
        return this;
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

    public static BaseStats fromString(String str) {
        String[] string = str.replaceAll("\\[", "")
                .replaceAll("]", "")
                .replaceAll(" ", "")
                .split(",");
        int [] values = Arrays.stream(string).mapToInt(Integer::parseInt).toArray();
        assert values.length == valueTypes;
        return new BaseStats(values);
    }

    public static Map<String, Integer> sum(List<BaseStats> list) {
        Map<String, Integer> retVal = new HashMap<>();
        for (int i = 1; i < valueTypes; i++) {
            int value = 0;
            for (BaseStats b: list) {
                value += b.values[i];
            }
            retVal.put(valuesName.get(i), value);
        }
        return retVal;
    }
}
