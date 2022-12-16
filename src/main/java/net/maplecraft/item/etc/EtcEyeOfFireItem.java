package net.maplecraft.item.etc;

import net.maplecraft.entity.boss.zakum.BossZakumSpawnEggItem;
import net.maplecraft.init.BlocksInit;
import net.maplecraft.init.TabsInit;
import net.maplecraft.item.MapleItem;
import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class EtcEyeOfFireItem extends MapleItem {
    public static String itemName = "etc_eye_of_fire";
    public EtcEyeOfFireItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.LEGENDARY)
                .properties(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                        .stacksTo(1)));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            BlockPos blockpos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(BlocksInit.ZAKUM_SPAWNER_CORE.get())) {
                Player player = context.getPlayer();
                if (!blockstate.getCollisionShape(level, blockpos).isEmpty()){
                    blockpos = blockpos.relative(direction);
                }
                BossZakumSpawnEggItem.spawnZakum((ServerLevel) level, null, context.getPlayer(), blockpos, MobSpawnType.SPAWN_EGG);
                if (player != null && !player.getAbilities().instabuild) {
                    context.getItemInHand().shrink(1);
                }
                return InteractionResult.CONSUME;
            } else {
                return InteractionResult.SUCCESS;
            }
        }
    }
}
