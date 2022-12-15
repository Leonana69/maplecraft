package net.maplecraft.entity.projectile;

import net.maplecraft.entity.MapleProjectileEntity;
import net.maplecraft.init.EntitiesInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

import java.util.List;

public class FireArrowEntity extends MapleProjectileEntity {
    public FireArrowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(EntitiesInit.FIRE_ARROW_ENTITY.get(), world);
    }

    public FireArrowEntity(EntityType<? extends FireArrowEntity> type, Level world) {
        super(type, world);
    }

    public FireArrowEntity(Level world, LivingEntity entity) {
        super(EntitiesInit.FIRE_ARROW_ENTITY.get(), entity, world);
    }

    @Override
    public String getProjectileName() {
        return "fire_arrow";
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        BlockPos blockPos = result.getBlockPos();
        Level world = this.level;

        List<BlockPos> list = List.of(blockPos.above(), blockPos.east(), blockPos.west(), blockPos.south(), blockPos.north());

        for (BlockPos pos : list) {
            if (world.getBlockState(pos).isAir() && this.random.nextInt(2) == 0) {
                world.setBlockAndUpdate(pos, BaseFireBlock.getState(world, pos));
                return;
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (isValidTarget(entity)) {
            entity.setSecondsOnFire(2);
        }
    }
}
