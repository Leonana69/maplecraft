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

public class SubiThrowingStarsEntity extends MapleProjectileEntity {
    public SubiThrowingStarsEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.SUBI_THROWING_STARS_ENTITY.get(), world);
    }

    public SubiThrowingStarsEntity(EntityType<? extends SubiThrowingStarsEntity> type, Level world) {
        super(type, world);
    }

    public SubiThrowingStarsEntity(LivingEntity entity, Level world) {
        super(EntitiesInit.SUBI_THROWING_STARS_ENTITY.get(), entity, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemsInit.UES_SUBI_THROWING_STARS.get());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
