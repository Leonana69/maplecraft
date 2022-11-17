package net.maplecraft.utils;

import net.minecraft.network.chat.Component;

import java.util.Arrays;
import java.util.List;

public class BaseEquipData {
    public final int max_star_force = 5;
    public EquipCategory category = EquipCategory.NONE;
    public BonusStats baseStats = new BonusStats();

    public int levels_req = 0;
    public int job_req = 0;

    public String description = "Default information";
}
