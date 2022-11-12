package net.maplecraft.utils;

import net.minecraft.ChatFormatting;

public class TextFormatter {
    public static String format(String input, ChatFormatting color) {
        StringBuilder sb = new StringBuilder(input.length() * 3);
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            sb.append(color.toString());
            sb.append(c);
        }

        return sb.toString();
    }
}
