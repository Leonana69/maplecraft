package net.maplecraft.entities.boss.zakum;

import net.maplecraft.init.EntitiesInit;
import net.maplecraft.init.TabsInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeSpawnEggItem;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class BossZakumSpawnEggItem extends ForgeSpawnEggItem {
    public static float zakumEntityScale = 1.5F;
    public static String itemName = "boss_zakum_spawn_egg";
    public BossZakumSpawnEggItem() {
        super(EntitiesInit.BOSS_ZAKUM_BODY_ENTITY, 0x948e8d, 0x3b3635, new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT).stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!(level instanceof ServerLevel)) {
            return InteractionResult.SUCCESS;
        } else {
            ItemStack itemStack = context.getItemInHand();
            BlockPos blockpos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(Blocks.SPAWNER)) {
                BlockEntity blockentity = level.getBlockEntity(blockpos);
                if (blockentity instanceof SpawnerBlockEntity) {
                    BaseSpawner basespawner = ((SpawnerBlockEntity)blockentity).getSpawner();
                    EntityType<?> entitytype1 = this.getType(itemStack.getTag());
                    basespawner.setEntityId(entitytype1);
                    blockentity.setChanged();
                    level.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
                    level.gameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, blockpos);
                    itemStack.shrink(1);
                    return InteractionResult.CONSUME;
                }
            }

            BlockPos blockPos1;
            if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
                blockPos1 = blockpos;
            } else {
                blockPos1 = blockpos.relative(direction);
            }

            Vec3 position = new Vec3(blockPos1.getX(), blockPos1.getY(), blockPos1.getZ());

            BossZakumBodyEntity bodyEntity = (BossZakumBodyEntity) customSpawn(this.getType(itemStack.getTag()), (ServerLevel) level, itemStack, context.getPlayer(), position, MobSpawnType.SPAWN_EGG);

            if (bodyEntity != null) {
                bodyEntity.setCustomName(Component.literal("Zakum Body"));
                for (int i = 0; i < 4; i++) {
                    Vec3 displace = new Vec3(2, 4 - 0.8 * i, -1).scale(zakumEntityScale);
                    BossZakumHandEntity handEntity = (BossZakumHandEntity) customSpawn(EntitiesInit.BOSS_ZAKUM_LEFT_HAND_ENTITY.get(),
                            (ServerLevel) level, itemStack, context.getPlayer(),
                            position.add(displace),
                            MobSpawnType.SPAWN_EGG);
                    if (handEntity != null) {
                        handEntity.setHandIndex(i);
                        handEntity.setZakumBodyEntity(bodyEntity);
                        bodyEntity.addTag("hand_" + i);
                    }
                }

                for (int i = 0; i < 4; i++) {
                    Vec3 displace = new Vec3(-2, 4 - 0.8 * i, -1).scale(zakumEntityScale);
                    BossZakumHandEntity handEntity = (BossZakumHandEntity) customSpawn(EntitiesInit.BOSS_ZAKUM_RIGHT_HAND_ENTITY.get(),
                            (ServerLevel) level, itemStack, context.getPlayer(),
                            position.add(displace),
                            MobSpawnType.SPAWN_EGG);
                    if (handEntity != null) {
                        handEntity.setHandIndex(i + 4);
                        handEntity.setZakumBodyEntity(bodyEntity);
                        bodyEntity.addTag("hand_" + (i + 4));
                    }
                }
            }

            return InteractionResult.CONSUME;
        }
    }

    public static Entity customSpawn(EntityType<?> entityType, ServerLevel world, @Nullable ItemStack itemStack, @Nullable Player player, Vec3 position, MobSpawnType type) {
        Entity entity = entityType.create(world);
        if (entity != null) {
            entity.moveTo(position.x + 0.5D, position.y, position.z + 0.5D, 0.0F, 0.0F);

            Component name = itemStack != null && itemStack.hasCustomHoverName() ? itemStack.getHoverName() : null;
            if (name != null && entity instanceof LivingEntity) {
                entity.setCustomName(name);
            }

            if (entity instanceof net.minecraft.world.entity.Mob
                    && net.minecraftforge.event.ForgeEventFactory.doSpecialSpawn(
                            (net.minecraft.world.entity.Mob) entity, world,
                            (float) position.x, (float) position.y, (float) position.z,
                            null, type))
                return null;
            world.addFreshEntityWithPassengers(entity);
        }
        return entity;
    }
}
