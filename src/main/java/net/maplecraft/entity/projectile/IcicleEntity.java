package net.maplecraft.entity.projectile;

import net.maplecraft.entity.MapleProjectileEntity;
import net.maplecraft.init.EntitiesInit;
import net.maplecraft.init.ItemsInit;
import net.maplecraft.item.MapleProjectileItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class IcicleEntity extends MapleProjectileEntity {
    private final MapleProjectileItem item = (MapleProjectileItem) ItemsInit.UES_ICICLE.get();

    public IcicleEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.ICICLE_ENTITY.get(), world);
    }

    public IcicleEntity(EntityType<? extends IcicleEntity> type, Level world) {
        super(type, world);
    }

    public IcicleEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.ICICLE_ENTITY.get(), entity, world);
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
