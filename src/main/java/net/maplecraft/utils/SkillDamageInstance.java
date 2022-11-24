package net.maplecraft.utils;

import net.minecraft.world.entity.LivingEntity;

import java.util.Comparator;
import java.util.List;

public class SkillDamageInstance {
    public int skillID = 0;
    public int attackCount = 0;
    public int maxAttackCount = 0;
    public long tick = 0;
    public int attackInterval = 0;
    public List<LivingEntity> targets;

    public SkillDamageInstance(int skillID, int attackCount, long tick, int attackInterval, List<LivingEntity> list) {
        this.skillID = skillID;
        this.attackCount = attackCount;
        this.maxAttackCount = attackCount;
        this.tick = tick;
        this.attackInterval = attackInterval;
        this.targets = list;
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
