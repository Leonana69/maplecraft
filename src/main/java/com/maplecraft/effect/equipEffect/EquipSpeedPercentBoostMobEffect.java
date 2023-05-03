package com.maplecraft.effect.equipEffect;

import com.maplecraft.effect.MapleMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.*;

public class EquipSpeedPercentBoostMobEffect extends MapleMobEffect {
    public EquipSpeedPercentBoostMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xff8103);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,
                "91AEAA56-376B-4498-935B-2F7F68517682",
                0.01D,
                AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public String getDescriptionId() {
        return "effect.maplecraft.equip_speed_percent_boost";
    }
}
