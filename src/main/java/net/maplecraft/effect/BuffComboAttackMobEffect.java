package net.maplecraft.effect;

import net.maplecraft.utils.MapleMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BuffComboAttackMobEffect extends MapleMobEffect {
    public BuffComboAttackMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
    }

    @Override
    public String getDescriptionId() {
        return "item.maplecraft.skill_combo_attack";
    }
}
