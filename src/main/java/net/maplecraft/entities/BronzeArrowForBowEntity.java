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

public class BronzeArrowForBowEntity extends MapleProjectileEntity {
    public BronzeArrowForBowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.BRONZE_ARROW_FOR_BOW_ENTITY.get(), world);
    }

    public BronzeArrowForBowEntity(EntityType<? extends BronzeArrowForBowEntity> type, Level world) {
        super(type, world);
    }

    public BronzeArrowForBowEntity(LivingEntity entity, Level world) {
        super(EntitiesInit.BRONZE_ARROW_FOR_BOW_ENTITY.get(), entity, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemsInit.USE_BRONZE_ARROW_FOR_BOW.get());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
