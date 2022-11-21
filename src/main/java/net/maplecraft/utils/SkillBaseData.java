package net.maplecraft.utils;

public class SkillBaseData {
    public int skillID;
    public EquipCategory weaponReq = EquipCategory.NONE;
    public int level = 0;
    public JobCategory jobReq = JobCategory.NONE;
    public double manaCost = 0.0D;
    public double healthCost = 0.0D;

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

    public SkillBaseData manaCost(double cost) {
        this.manaCost = cost;
        return this;
    }

    public SkillBaseData healthCost(double cost) {
        this.healthCost = cost;
        return this;
    }

    public SkillBaseData jobReq(JobCategory j) {
        this.jobReq = j;
        return this;
    }
}
