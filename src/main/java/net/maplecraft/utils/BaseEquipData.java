package net.maplecraft.utils;

public class BaseEquipData {
    public final int max_star_force = 5;
    public EquipCategory category = EquipCategory.NONE;
    public BaseStats baseStats = new BaseStats();

    public int levels_req = 0;
    public int job_req = 0;

    public String description = "Default information";
}
