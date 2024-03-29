package com.maplecraft.effect.buffEffect;

import com.maplecraft.effect.MapleMobEffect;
import com.maplecraft.init.EffectsInit;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BuffComboAttackMobEffect extends MapleMobEffect {
    static int lastEffectTick = -1;
    public BuffComboAttackMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x15aaff);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                "958D7064-02B3-2F59-21BE-B3C23A327B82",
                0.02D,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public String getDescriptionId() {
        return "item.maplecraft.skill_combo_attack";
    }

    @Override
    public void applyEffectTick(LivingEntity player, int p_19468_) {
        if (!player.level.isClientSide) {
            MobEffectInstance instance = player.getEffect(EffectsInit.BUFF_COMBO_ATTACK.get());
            if (instance != null && lastEffectTick < player.tickCount) {
                lastEffectTick = player.tickCount;
                int amplifier = instance.getAmplifier();


            }
        }
    }

//    public void applyPlayerEffect(LivingEntity player, int amplifier) {
//        if (amplifier > 0) {
//            SkillEffectInstance instance1 = new SkillEffectInstance()
//                    .skillName("buff_combo_attack_effect_" + amplifier)
//                    .animeCount(1)
//                    .delay(0)
//                    .tickPerFrame(1)
//                    .hitEffectOnHit(false)
//                    .fixedPosition(false)
//                    .textureSize(140, 140);
//            instance1.targets = List.of(player);
////                instance1.translate = new Vec3(1, -1, 1);
//            DelayedDamageHandler.hitEffectList.add(instance1);
//        }
//    }
}
