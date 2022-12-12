package net.maplecraft.effect;

import net.maplecraft.utils.MapleMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BuffHolyDragonMobEffect extends MapleMobEffect {
    private LivingEntity summonedEntity = null;
    public BuffHolyDragonMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x03B9FF);
    }

    @Override
    public String getDescriptionId() {
        return "item.maplecraft.skill_holy_dragon";
    }

    public void setSummonedEntity(LivingEntity entity) {
        if (!entity.level.isClientSide) {
            if (summonedEntity != null && !summonedEntity.isRemoved()) {
                summonedEntity.remove(Entity.RemovalReason.KILLED);
            }
            this.summonedEntity = entity;
        }
    }
}
