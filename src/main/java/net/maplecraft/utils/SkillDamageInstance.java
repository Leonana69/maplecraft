package net.maplecraft.utils;

import net.minecraft.world.entity.LivingEntity;

import java.util.Comparator;
import java.util.List;

public class SkillDamageInstance {
    public int skillID = 0;
    public float attackDamage = 0;
    public int attackCount = 0;
    public int maxAttackCount = 0;
    public long tick = 0;
    public int attackInterval = 0;
    public LivingEntity target;

    public SkillDamageInstance(int skillID, float attackDamage, int attackCount, long tick, int attackInterval, LivingEntity target) {
        this.skillID = skillID;
        this.attackDamage = attackDamage;
        this.attackCount = attackCount;
        this.maxAttackCount = attackCount;
        this.tick = tick;
        this.attackInterval = attackInterval;
        this.target = target;
    }

    public static class SkillDamageInstanceComparator implements Comparator<SkillDamageInstance> {
        public int compare(SkillDamageInstance s1, SkillDamageInstance s2) {
            if (s1.tick > s2.tick)
                return 1;
            else if (s1.tick < s2.tick)
                return -1;
            return 0;
        }
    }
}
