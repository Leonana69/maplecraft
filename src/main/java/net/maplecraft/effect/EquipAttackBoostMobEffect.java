package net.maplecraft.effect;

import net.maplecraft.utils.EquipMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class EquipAttackBoostMobEffect extends EquipMobEffect {
    public EquipAttackBoostMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A517682", (double)0.25D, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public String getDescriptionId() {
        return "E Attack Boost";
    }
}
