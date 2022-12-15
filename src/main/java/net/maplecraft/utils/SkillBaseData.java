package net.maplecraft.utils;

import java.util.ArrayList;
import java.util.List;

public class SkillBaseData {
    public int skillID;
    public List<EquipCategory> weaponReq = new ArrayList<>();
    public JobCategory jobReq = JobCategory.NONE;
    public boolean isMagic = false;
    public double manaCost = 0.0D;
    public double healthCost = 0.0D;
    public int damage = 100;
    public int delay = 8;
    public int attackCount = 1;
    public int attackInterval = 6;

    public SkillBaseData setAttackInterval(int i) {
        this.attackInterval = i;
        return this;
    }

    public SkillBaseData setSkillID(int i) {
        this.skillID = i;
        return this;
    }

    public SkillBaseData setIsMagic(boolean b) {
        this.isMagic = b;
        return this;
    }

    public SkillBaseData setWeaponReq(EquipCategory e) {
        this.weaponReq.add(e);
        return this;
    }

    public SkillBaseData setManaCost(double cost) {
        this.manaCost = cost;
        return this;
    }

    public SkillBaseData setHealthCost(double cost) {
        this.healthCost = cost;
        return this;
    }

    public SkillBaseData setJobReq(JobCategory j) {
        this.jobReq = j;
        return this;
    }

    public SkillBaseData setDamage(int i) {
        this.damage = i;
        return this;
    }

    public SkillBaseData setAttackCount(int i) {
        this.attackCount = i;
        return this;
    }

    public SkillBaseData setDelay(int i) {
        this.delay = i;
        return this;
    }
}
