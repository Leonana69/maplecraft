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

public class SteelArrowForBowEntity extends MapleProjectileEntity {
    private final MapleProjectileItem item = (MapleProjectileItem) ItemsInit.USE_STEEL_ARROW_FOR_BOW.get();

    public SteelArrowForBowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.STEEL_ARROW_FOR_BOW_ENTITY.get(), world);
    }

    public SteelArrowForBowEntity(EntityType<? extends SteelArrowForBowEntity> type, Level world) {
        super(type, world);
    }

    public SteelArrowForBowEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.STEEL_ARROW_FOR_BOW_ENTITY.get(), entity, world);
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
