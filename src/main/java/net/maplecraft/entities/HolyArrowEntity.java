package net.maplecraft.entities;

import net.maplecraft.init.EntitiesInit;
import net.maplecraft.init.ItemsInit;
import net.maplecraft.utils.MapleProjectileEntity;
import net.maplecraft.utils.MapleProjectileItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

import java.util.Comparator;
import java.util.List;

public class HolyArrowEntity extends MapleProjectileEntity {
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
