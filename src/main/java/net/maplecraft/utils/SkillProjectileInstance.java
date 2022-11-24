package net.maplecraft.utils;

import net.minecraft.world.phys.Vec3;

import java.util.Comparator;

public class SkillProjectileInstance {
    public int skillID;
    public MapleProjectileEntity entity;
    public Vec3 shootDirection;
    public long tick = 0;

    public SkillProjectileInstance(int skillID, MapleProjectileEntity entity, Vec3 shootDirection, long tick) {
        this.skillID = skillID;
        this.entity = entity;
        this.shootDirection = shootDirection;
        this.tick = tick;
    }

    public static class SkillProjectileInstanceComparator implements Comparator<SkillProjectileInstance> {
        public int compare(SkillProjectileInstance s1, SkillProjectileInstance s2) {
            if (s1.tick > s2.tick)
                return 1;
            else if (s1.tick < s2.tick)
                return -1;
            return 0;
        }
    }
}
