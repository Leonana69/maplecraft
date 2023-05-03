package com.maplecraft.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PotentialStats {
    public MapleRarity rarity = MapleRarity.COMMON;
    public PotentialType type = PotentialType.NONE;

    public PotentialStats() {}
    public PotentialStats(MapleRarity rarity, PotentialType type) {
        this.rarity = rarity;
        this.type = type;
    }

    public static Map<String, Integer> sum(List<PotentialStats> list) {
        Map<String, Integer> retVal = new HashMap<>();
        for (int i = 1; i < PotentialType.SIZE; i++) {
            PotentialType p = PotentialType.VALUES.get(i);
            int value = 0;
            for (PotentialStats lp: list) {
                if (Objects.equals(p.typeName, lp.type.typeName)) {
                    value += lp.rarity.type * lp.type.amplifier;
                }
            }
            retVal.put(p.typeName, value);
        }
        return retVal;
    }

    public static PotentialStats [] getDefaultPotentialStats() {
        return new PotentialStats [] {
                new PotentialStats(MapleRarity.COMMON, PotentialType.NONE),
                new PotentialStats(MapleRarity.COMMON, PotentialType.NONE),
                new PotentialStats(MapleRarity.COMMON, PotentialType.NONE)
        };
    }

    public String toString() {
        int value = rarity.type * type.amplifier;
        String percent = type.isPercent ? "%" : "";
        return type == PotentialType.NONE ? "" : type.typeName + ": +" + value + percent;
    }
}
