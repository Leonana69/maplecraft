package net.maplecraft.effect;

import net.maplecraft.utils.EquipMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.*;

public class EquipSpeedBoostMobEffect extends EquipMobEffect {
    public EquipSpeedBoostMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68517682", (double)0.002D, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public String getDescriptionId() {
        return "E Speed Boost";
    }
}
