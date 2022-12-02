package net.maplecraft.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static net.maplecraft.effect.EquipAttackPercentBoostMobEffect.equipAttackPercentBoostBase;
import static net.maplecraft.effect.EquipJumpBoost.equipJumpPercentBoostBase;
import static net.maplecraft.effect.EquipSpeedPercentBoostMobEffect.equipSpeedPercentBoostBase;
import static net.maplecraft.effect.PlayerDefenseBoost.equipDefensePercentBoostBase;

public class PotentialStats {
    public MapleRarity rarity = MapleRarity.COMMON;
    public PotentialType type = PotentialType.NONE;

    PotentialStats(MapleRarity rarity, PotentialType type) {
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
                    value += lp.rarity.type;
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
        int value = rarity.type;
        String percent = "%";
        switch (type.type) {
            case 1 -> percent = "";
            case 2, 3 -> value *= equipAttackPercentBoostBase;
            case 4, 5 -> value *= equipSpeedPercentBoostBase;
            case 6 -> value *= equipJumpPercentBoostBase;
            case 7 -> value *= equipDefensePercentBoostBase;
        }

        return type == PotentialType.NONE ? "" : type.typeName + ": +" + value + percent;
    }
}
