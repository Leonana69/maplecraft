package net.maplecraft.entities;

import net.maplecraft.init.EntitiesInit;
import net.maplecraft.init.ItemsInit;
import net.maplecraft.utils.MapleProjectileEntity;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class SteelyThrowingKnivesEntity extends MapleProjectileEntity {
    public SteelyThrowingKnivesEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.STEELY_THROWING_KNIVES_ENTITY.get(), world);
    }

    public SteelyThrowingKnivesEntity(EntityType<? extends SteelyThrowingKnivesEntity> type, Level world) {
        super(type, world);
    }

    public SteelyThrowingKnivesEntity(LivingEntity entity, Level world) {
        super(EntitiesInit.STEELY_THROWING_KNIVES_ENTITY.get(), entity, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemsInit.USE_STEELY_THROWING_KNIVES.get());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
