package com.maplecraft.effect.equipEffect;

import com.maplecraft.effect.MapleMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class EquipAttackPercentBoostMobEffect extends MapleMobEffect {
    public EquipAttackPercentBoostMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,
                "325D7064-6A63-1F59-CABE-C2C23A517682",
                0.01D,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public String getDescriptionId() {
        return "effect.maplecraft.equip_attack_percent_boost";
    }
}
