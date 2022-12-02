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

public class DiamondArrowForBowEntity extends MapleProjectileEntity {
    private final MapleProjectileItem item = (MapleProjectileItem) ItemsInit.USE_DIAMOND_ARROW_FOR_BOW.get();

    public DiamondArrowForBowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.DIAMOND_ARROW_FOR_BOW_ENTITY.get(), world);
    }

    public DiamondArrowForBowEntity(EntityType<? extends DiamondArrowForBowEntity> type, Level world) {
        super(type, world);
    }

    public DiamondArrowForBowEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.DIAMOND_ARROW_FOR_BOW_ENTITY.get(), entity, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return canPickUp ? new ItemStack(item) : ItemStack.EMPTY;
    }

    @Override
    public String getProjectileName() {
        return item.itemName;
    }
}
