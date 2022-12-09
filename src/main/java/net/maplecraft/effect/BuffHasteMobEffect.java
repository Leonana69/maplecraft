package net.maplecraft.effect;

import net.maplecraft.utils.MapleMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BuffHasteMobEffect extends MapleMobEffect {
    public BuffHasteMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "19AEAC56-3B6E-4498-9A5B-123468517682", 0.001D, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public String getDescriptionId() {
        return "item.maplecraft.skill_haste";
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        JumpPercentBoostMobEffect.buffValue = amplifier;
    }
}
