package net.maplecraft.effect;

import net.maplecraft.procedures.DelayedDamageHandler;
import net.maplecraft.utils.MapleMobEffect;
import net.maplecraft.utils.SkillEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.List;

public class BuffComboAttackMobEffect extends MapleMobEffect {
    public BuffComboAttackMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
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
        MobEffectInstance instance = player.getEffect(this);
        if (instance != null) {
            int amplifier = instance.getAmplifier();

            SkillEffectInstance instance1 = new SkillEffectInstance()
                    .skillName("buff_combo_attack_effect")
                    .animeCount(1)
                    .delay(0)
                    .tickPerFrame(1)
                    .hitEffectOnHit(false)
                    .textureSize(210, 42);
            instance1.tick = player.level.getGameTime();
            instance1.targets = List.of(player);
            DelayedDamageHandler.hitEffectList.add(instance1);
        }
    }
}
