package net.maplecraft.effect;

import net.maplecraft.utils.MapleMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BuffRageMobEffect extends MapleMobEffect {
    public BuffRageMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                "9E5D7064-3CB3-2F59-21BE-B3C23A517682",
                0.01D,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public String getDescriptionId() {
        return "item.maplecraft.skill_rage";
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        DefensePercentBoostMobEffect.buffValueN = amplifier;
    }
}
