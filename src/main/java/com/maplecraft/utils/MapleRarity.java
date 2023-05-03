package com.maplecraft.utils;

import net.minecraft.ChatFormatting;

public enum MapleRarity {
    NONE(ChatFormatting.BLACK, -1, "N"),
    COMMON(ChatFormatting.WHITE, 0, "C"),
    RARE(ChatFormatting.BLUE, 1, "R"),
    EPIC(ChatFormatting.DARK_PURPLE, 2, "E"),
    UNIQUE(ChatFormatting.GOLD, 3, "U"),
    LEGENDARY(ChatFormatting.GREEN, 4, "L");

    public final ChatFormatting color;
    public final int type;
    public final String typeName;

    MapleRarity(ChatFormatting p_43028_, int type, String typeName) {
        this.color = p_43028_;
        this.type = type;
        this.typeName = typeName;
    }

    public static MapleRarity get(int type) {
        assert type >= -1 && type <= 4;
        MapleRarity rarity = NONE;
        switch (type) {
            case 0 -> rarity = COMMON;
            case 1 -> rarity = RARE;
            case 2 -> rarity = EPIC;
            case 3 -> rarity = UNIQUE;
            case 4 -> rarity = LEGENDARY;
        }
        return rarity;
    }
}
