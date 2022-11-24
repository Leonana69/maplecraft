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

public class ArrowForBowEntity extends MapleProjectileEntity {
    private final MapleProjectileItem item = (MapleProjectileItem) ItemsInit.USE_ARROW_FOR_BOW.get();

    public ArrowForBowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.ARROW_FOR_BOW_ENTITY.get(), world);
    }

    public ArrowForBowEntity(EntityType<? extends ArrowForBowEntity> type, Level world) {
        super(type, world);
    }

    public ArrowForBowEntity(LivingEntity entity, Level world) {
        super(EntitiesInit.ARROW_FOR_BOW_ENTITY.get(), entity, world);
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
