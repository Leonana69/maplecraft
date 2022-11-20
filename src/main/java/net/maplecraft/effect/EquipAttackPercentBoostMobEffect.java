package net.maplecraft.effect;

import net.maplecraft.utils.EquipMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class EquipAttackPercentBoostMobEffect extends EquipMobEffect {
    public EquipAttackPercentBoostMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, "325D7064-6A63-1F59-CABE-C2C23A517682", (double)0.05D, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public String getDescriptionId() {
        return "E Attack Percent Boost";
    }
}
