package net.maplecraft.utils;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SkillHitEffectInstance {
//    THUNDER_BOLT("skill_thunder_bolt", 8, 3, 68, 219);

    public String skillName;
    public int animeCount = 0;
    public int currentAnime = -1;
    public long tick;
    public int delay = 0;
    public boolean hitEffectOnHit = true;
    public int tickPerFrame = 2;
    public int textureWidth;
    public int textureHeight;
    public List<Vec3> renderPos;
    public List<LivingEntity> targets;

    public SkillHitEffectInstance(SkillHitEffectInstance other) {
        this.skillName = other.skillName;
        this.animeCount = other.animeCount;
        this.currentAnime = other.currentAnime;
        this.tick = other.tick;
        this.delay = other.delay;
        this.textureWidth = other.textureWidth;
        this.textureHeight = other.textureHeight;
    }

    public SkillHitEffectInstance() {}

    public SkillHitEffectInstance skillName(String s) {
        this.skillName = s;
        return this;
    }

    public SkillHitEffectInstance animeCount(int i) {
        this.animeCount = i;
        return this;
    }

    public SkillHitEffectInstance delay(int i) {
        this.delay = i;
        return this;
    }

    public SkillHitEffectInstance textureSize(int w, int h) {
        this.textureWidth = w;
        this.textureHeight = h;
        return this;
    }

    public SkillHitEffectInstance hitEffectOnHit(boolean b) {
        this.hitEffectOnHit = b;
        return this;
    }
}
