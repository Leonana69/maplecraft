package net.maplecraft.effect;

import net.maplecraft.utils.MapleMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BuffIronWillMobEffect extends MapleMobEffect {
    public BuffIronWillMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
    }

    @Override
    public String getDescriptionId() {
        return "effect.maplecraft.buff_iron_will";
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        DefensePercentBoostMobEffect.buffValue = amplifier;
    }
}
