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

public class BalancedFuryEntity extends MapleProjectileEntity {
    public static String entityName = "balanced_fury_entity";
    private final MapleProjectileItem item = (MapleProjectileItem) ItemsInit.USE_BALANCED_FURY.get();

    public BalancedFuryEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.BALANCED_FURY_ENTITY.get(), world);
    }

    public BalancedFuryEntity(EntityType<? extends BalancedFuryEntity> type, Level world) {
        super(type, world);
    }

    public BalancedFuryEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.BALANCED_FURY_ENTITY.get(), entity, world);
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
