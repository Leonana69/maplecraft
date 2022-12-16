package net.maplecraft.entity.boss.zakum;

import net.maplecraft.init.EntitiesInit;
import net.maplecraft.init.TabsInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
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


public class BossZakumSpawnEggItem extends ForgeSpawnEggItem {
    public static float zakumEntityScale = 1.5F;
    public static String itemName = "boss_zakum_spawn_egg";
    public BossZakumSpawnEggItem() {
        super(EntitiesInit.BOSS_ZAKUM_BODY_ENTITY, 0x948e8d, 0x3b3635, new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT).stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide) {
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

            if (!blockstate.getCollisionShape(level, blockpos).isEmpty()){
                blockpos = blockpos.relative(direction);
            }

            spawnZakum((ServerLevel) level, context.getItemInHand(), context.getPlayer(), blockpos, MobSpawnType.SPAWN_EGG);
            return InteractionResult.CONSUME;
        }
    }

    public static void spawnZakum(ServerLevel level, @Nullable ItemStack itemStack, @Nullable Player player, BlockPos blockPos, MobSpawnType spawnType) {
        Vec3 position = new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        BossZakumBodyEntity bodyEntity = (BossZakumBodyEntity) customSpawn(EntitiesInit.BOSS_ZAKUM_BODY_ENTITY.get(),
                level, player,
                position, spawnType);
        if (bodyEntity != null) {
            if (player != null && itemStack != null && !player.getAbilities().instabuild) {
                itemStack.shrink(1);
                level.gameEvent(player, GameEvent.ENTITY_PLACE, blockPos);
            }
            bodyEntity.setCustomName(Component.literal("Zakum Body"));
            bodyEntity.setHandCount(8);

            for (int i = 0; i < 4; i++) {
                Vec3 displace = new Vec3(2, 4 - 0.8 * i, -1).scale(zakumEntityScale);
                BossZakumHandEntity handEntity = (BossZakumHandEntity) customSpawn(EntitiesInit.BOSS_ZAKUM_LEFT_HAND_ENTITY.get(),
                        level, player,
                        position.add(displace),
                        spawnType);
                if (handEntity != null) {
                    handEntity.setHandIndex(i);
                    handEntity.setBodyId(bodyEntity);
                }
            }

            for (int i = 0; i < 4; i++) {
                Vec3 displace = new Vec3(-2, 4 - 0.8 * i, -1).scale(zakumEntityScale);
                BossZakumHandEntity handEntity = (BossZakumHandEntity) customSpawn(EntitiesInit.BOSS_ZAKUM_RIGHT_HAND_ENTITY.get(),
                        level, player,
                        position.add(displace),
                        spawnType);
                if (handEntity != null) {
                    handEntity.setHandIndex(i + 4);
                    handEntity.setBodyId(bodyEntity);
                }
            }
        }
    }

    public static Entity customSpawn(EntityType<?> entityType, ServerLevel world, @Nullable Player player, Vec3 position, MobSpawnType type) {
        Entity entity = entityType.create(world);
        if (entity != null) {
            entity.moveTo(position.x, position.y, position.z, 0, 0);
            world.addFreshEntity(entity);
        }
        return entity;
    }
}
