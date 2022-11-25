package net.maplecraft.entities;

import net.maplecraft.init.EntitiesInit;
import net.maplecraft.init.ItemsInit;
import net.maplecraft.utils.MapleProjectileEntity;
import net.maplecraft.utils.MapleProjectileItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class BalancedFuryEntity extends MapleProjectileEntity {
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
        return new ItemStack(item);
    }

    @Override
    public String getProjectileName() {
        return item.itemName;
    }
}
