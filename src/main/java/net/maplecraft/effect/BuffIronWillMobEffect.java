package net.maplecraft.effect;

import net.maplecraft.utils.MapleMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class BuffIronWillMobEffect extends MapleMobEffect {
    public BuffIronWillMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x668899);
    }

    @Override
    public String getDescriptionId() {
        return "item.maplecraft.skill_iron_will";
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        DefensePercentBoostMobEffect.buffValueP = amplifier;
    }
}
