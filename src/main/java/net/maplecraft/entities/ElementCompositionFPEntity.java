package net.maplecraft.entities;

import net.maplecraft.init.EntitiesInit;
import net.maplecraft.utils.MapleProjectileEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class ElementCompositionFPEntity extends MapleProjectileEntity {
    public ElementCompositionFPEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.ELEMENT_COMPOSITION_FP_ENTITY.get(), world);
    }

    public ElementCompositionFPEntity(EntityType<? extends ElementCompositionFPEntity> type, Level world) {
        super(type, world);
    }

    public ElementCompositionFPEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.ELEMENT_COMPOSITION_FP_ENTITY.get(), entity, world);
    }

    @Override
    public String getProjectileName() {
        return "element_composition_fp";
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (!this.inGround) {
            super.onHitEntity(result);
            Entity entity = result.getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 2));
            }
        }
    }
}
