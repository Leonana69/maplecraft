package net.maplecraft.effect;

import net.maplecraft.utils.EquipMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class EquipHealthBoostMobEffect extends EquipMobEffect {
    public EquipHealthBoostMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
        this.addAttributeModifier(Attributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5C517682", (double)1.0D, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public String getDescriptionId() {
        return "E Health Boost";
    }
}
