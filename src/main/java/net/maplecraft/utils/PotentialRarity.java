package net.maplecraft.utils;

import net.minecraft.ChatFormatting;

public enum PotentialRarity {
    COMMON(ChatFormatting.WHITE, 0, "C"),
    RARE(ChatFormatting.BLUE, 1, "R"),
    EPIC(ChatFormatting.DARK_PURPLE, 2, "E"),
    UNIQUE(ChatFormatting.GOLD, 3, "U"),
    LEGENDARY(ChatFormatting.GREEN, 4, "L");

    public final ChatFormatting color;
    public final int type;
    public final String typeName;

    PotentialRarity(ChatFormatting p_43028_, int type, String typeName) {
        this.color = p_43028_;
        this.type = type;
        this.typeName = typeName;
    }

    public static PotentialRarity get(int type) {
        assert type >=0 && type <= 4;
        switch (type) {
            case 0 -> {
                return COMMON;
            }
            case 1 -> {
                return RARE;
            }
            case 2 -> {
                return EPIC;
            }
            case 3 -> {
                return UNIQUE;
            }
            case 4 -> {
                return LEGENDARY;
            }
        }
        return null;
    }
}
