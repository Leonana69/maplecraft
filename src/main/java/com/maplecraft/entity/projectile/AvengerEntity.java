package com.maplecraft.entity.projectile;

import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.init.EntitiesInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class AvengerEntity extends MapleProjectileEntity {
    public static String entityName = "avenger_entity";
    public AvengerEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.AVENGER_ENTITY.get(), world);
    }

    public AvengerEntity(EntityType<? extends AvengerEntity> type, Level world) {
        super(type, world);
    }

    public AvengerEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.AVENGER_ENTITY.get(), entity, world);
    }

    @Override
    public String getProjectileName() {
        return "avenger";
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }
}
