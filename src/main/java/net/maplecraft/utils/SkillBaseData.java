package net.maplecraft.utils;

public class SkillBaseData {
    public int skillID;
    public EquipCategory weaponReq = EquipCategory.NONE;
    public int level = 0;
    public JobCategory jobReq = JobCategory.NONE;
    public double manaCostBase = 0.0D;
    public double manaCostReduction = 0.0D;

    public SkillBaseData level(int l) {
        this.level = l;
        return this;
    }

    public SkillBaseData skillID(int i) {
        this.skillID = i;
        return this;
    }

    public SkillBaseData weaponReq(EquipCategory e) {
        this.weaponReq = e;
        return this;
    }

    public SkillBaseData manaCost(double base, double reduction) {
        this.manaCostBase = base;
        this.manaCostReduction = reduction;
        return this;
    }

    public SkillBaseData jobReq(JobCategory j) {
        this.jobReq = j;
        return this;
    }
}
