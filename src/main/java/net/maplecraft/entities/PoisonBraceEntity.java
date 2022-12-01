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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.PlayMessages;

public class PoisonBraceEntity extends MapleProjectileEntity {
    public PoisonBraceEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.POISON_BRACE_ENTITY.get(), world);
    }

    public PoisonBraceEntity(EntityType<? extends PoisonBraceEntity> type, Level world) {
        super(type, world);
    }

    public PoisonBraceEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.POISON_BRACE_ENTITY.get(), entity, world);
    }

    @Override
    public String getProjectileName() {
        return "poison_brace";
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
        }
    }
}
