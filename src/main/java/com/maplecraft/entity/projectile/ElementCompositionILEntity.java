package com.maplecraft.entity.projectile;

import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.init.EntitiesInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class ElementCompositionILEntity extends MapleProjectileEntity {
    public static String entityName = "element_composition_il_entity";
    public ElementCompositionILEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.ELEMENT_COMPOSITION_IL_ENTITY.get(), world);
    }

    public ElementCompositionILEntity(EntityType<? extends ElementCompositionILEntity> type, Level world) {
        super(type, world);
    }

    public ElementCompositionILEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.ELEMENT_COMPOSITION_IL_ENTITY.get(), entity, world);
    }

    @Override
    public String getProjectileName() {
        return "element_composition_il";
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (isValidTarget(entity)) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2));
        }
    }
}
