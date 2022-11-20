package net.maplecraft.effect;

import net.maplecraft.utils.EquipMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class EquipAttackSpeedBoostMobEffect extends EquipMobEffect {
    public EquipAttackSpeedBoostMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
        this.addAttributeModifier(Attributes.ATTACK_SPEED, "AF8B6E3F-33A8-4C0A-AA36-5BA2BB517682", (double)0.03D, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public String getDescriptionId() {
        return "E Attack Speed Boost";
    }
}
