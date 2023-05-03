package com.maplecraft.entity.projectile;

import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.init.EntitiesInit;
import com.maplecraft.init.ItemsInit;
import com.maplecraft.item.MapleProjectileItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class SubiThrowingStarsEntity extends MapleProjectileEntity {
    private final MapleProjectileItem item = (MapleProjectileItem) ItemsInit.UES_SUBI_THROWING_STARS.get();

    public SubiThrowingStarsEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.SUBI_THROWING_STARS_ENTITY.get(), world);
    }

    public SubiThrowingStarsEntity(EntityType<? extends SubiThrowingStarsEntity> type, Level world) {
        super(type, world);
    }

    public SubiThrowingStarsEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.SUBI_THROWING_STARS_ENTITY.get(), entity, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return canPickUp() ? new ItemStack(item) : ItemStack.EMPTY;
    }

    @Override
    public String getProjectileName() {
        return item.itemName;
    }
}
