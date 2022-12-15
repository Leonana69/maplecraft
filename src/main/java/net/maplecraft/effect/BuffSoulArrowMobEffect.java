package net.maplecraft.effect;

import net.maplecraft.utils.MapleMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BuffSoulArrowMobEffect extends MapleMobEffect {
    public BuffSoulArrowMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xfee169);
    }

    @Override
    public String getDescriptionId() {
        return "item.maplecraft.skill_soul_arrow";
    }
}
