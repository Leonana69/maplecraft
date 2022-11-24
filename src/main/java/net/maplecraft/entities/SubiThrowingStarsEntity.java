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

public class SubiThrowingStarsEntity extends MapleProjectileEntity {
    private final MapleProjectileItem item = (MapleProjectileItem) ItemsInit.UES_SUBI_THROWING_STARS.get();

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
        return new ItemStack(item);
    }

    @Override
    public String getProjectileName() {
        return item.itemName;
    }
}
