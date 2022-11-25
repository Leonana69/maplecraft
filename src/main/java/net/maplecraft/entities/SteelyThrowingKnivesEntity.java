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

public class SteelyThrowingKnivesEntity extends MapleProjectileEntity {
    private final MapleProjectileItem item = (MapleProjectileItem) ItemsInit.USE_STEELY_THROWING_KNIVES.get();

    public SteelyThrowingKnivesEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.STEELY_THROWING_KNIVES_ENTITY.get(), world);
    }

    public SteelyThrowingKnivesEntity(EntityType<? extends SteelyThrowingKnivesEntity> type, Level world) {
        super(type, world);
    }

    public SteelyThrowingKnivesEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.STEELY_THROWING_KNIVES_ENTITY.get(), entity, world);
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
