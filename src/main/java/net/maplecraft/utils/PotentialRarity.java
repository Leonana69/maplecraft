package net.maplecraft.utils;

import net.minecraft.ChatFormatting;

public enum PotentialRarity {
    COMMON(ChatFormatting.WHITE, 0, 'C'),
    RARE(ChatFormatting.BLUE, 1, 'R'),
    EPIC(ChatFormatting.DARK_PURPLE, 2, 'E'),
    UNIQUE(ChatFormatting.GOLD, 3, 'U'),
    LEGENDARY(ChatFormatting.GREEN, 4, 'L');

    public final ChatFormatting color;
    public final int type;
    public final char typeName;

    private PotentialRarity(ChatFormatting p_43028_, int type, char typeName) {
        this.color = p_43028_;
        this.type = type;
        this.typeName = typeName;
    }
}
