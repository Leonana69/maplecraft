package net.maplecraft.utils;

import java.util.ArrayList;
import java.util.List;

public class SkillBaseData {
    public int skillID;
    public List<EquipCategory> weaponReq = new ArrayList<>();
    public int level = 0;
    public JobCategory jobReq = JobCategory.NONE;
    public boolean isMagic = false;
    public double manaCost = 0.0D;
    public double healthCost = 0.0D;
    public int damage = 100;
    public int delay = 8;
    public int attackCount = 1;
    public int attackInterval = 6;

    public SkillBaseData level(int i) {
        this.level = i;
        return this;
    }

    public SkillBaseData attackInterval(int i) {
        this.attackInterval = i;
        return this;
    }

    public SkillBaseData skillID(int i) {
        this.skillID = i;
        return this;
    }

    public SkillBaseData isMagic(boolean b) {
        this.isMagic = b;
        return this;
    }

    public SkillBaseData weaponReq(EquipCategory e) {
        this.weaponReq.add(e);
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

    public SkillBaseData damage(int i) {
        this.damage = i;
        return this;
    }

    public SkillBaseData attackCount(int i) {
        this.attackCount = i;
        return this;
    }

    public SkillBaseData delay(int i) {
        this.delay = i;
        return this;
    }
}
