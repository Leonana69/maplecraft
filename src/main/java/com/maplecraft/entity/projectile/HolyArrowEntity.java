package com.maplecraft.entity.projectile;

import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.init.EntitiesInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class HolyArrowEntity extends MapleProjectileEntity {
    public static String entityName = "holy_arrow_entity";
    public HolyArrowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.HOLY_ARROW_ENTITY.get(), world);
    }

    public HolyArrowEntity(EntityType<? extends HolyArrowEntity> type, Level world) {
        super(type, world);
    }

    public HolyArrowEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.HOLY_ARROW_ENTITY.get(), entity, world);
    }

    @Override
    public String getProjectileName() {
        return "holy_arrow";
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }
}
