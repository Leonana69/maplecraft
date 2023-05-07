package com.maplecraft.entity.projectile;

import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.item.MapleProjectileItem;
import com.maplecraft.init.EntitiesInit;
import com.maplecraft.init.ItemsInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class SteelArrowForBowEntity extends MapleProjectileEntity {
    public static String entityName = "steel_arrow_for_bow_entity";
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
