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

public class BronzeArrowForBowEntity extends MapleProjectileEntity {
    public static String entityName = "bronze_arrow_for_bow_entity";
    private final MapleProjectileItem item = (MapleProjectileItem) ItemsInit.USE_BRONZE_ARROW_FOR_BOW.get();

    public BronzeArrowForBowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.BRONZE_ARROW_FOR_BOW_ENTITY.get(), world);
    }

    public BronzeArrowForBowEntity(EntityType<? extends BronzeArrowForBowEntity> type, Level world) {
        super(type, world);
    }

    public BronzeArrowForBowEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.BRONZE_ARROW_FOR_BOW_ENTITY.get(), entity, world);
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
