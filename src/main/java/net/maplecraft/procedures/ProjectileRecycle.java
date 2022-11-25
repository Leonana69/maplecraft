package net.maplecraft.procedures;

import net.maplecraft.entities.*;
import net.maplecraft.init.ItemsInit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ProjectileRecycle {
    @SubscribeEvent
    public static void onLivingEntityDied(LivingDeathEvent event) {
        LivingEntity target = event.getEntity();
        Level world = target.getLevel();
        if (!world.isClientSide) {
            Entity entity = event.getSource().getDirectEntity();
            ItemLike projectile = null;

            if (entity instanceof SubiThrowingStarsEntity) {
                projectile = ItemsInit.UES_SUBI_THROWING_STARS.get();
            } else if (entity instanceof SteelyThrowingKnivesEntity) {
                projectile = ItemsInit.USE_STEELY_THROWING_KNIVES.get();
            } else if (entity instanceof BalancedFuryEntity) {
                projectile = ItemsInit.USE_BALANCED_FURY.get();
            } else if (entity instanceof ArrowForBowEntity) {
                projectile = ItemsInit.USE_ARROW_FOR_BOW.get();
            } else if (entity instanceof BronzeArrowForBowEntity) {
                projectile = ItemsInit.USE_BRONZE_ARROW_FOR_BOW.get();
            } else if (entity instanceof DiamondArrowForBowEntity) {
                projectile = ItemsInit.USE_DIAMOND_ARROW_FOR_BOW.get();
            }

            if (projectile != null) {
                ItemEntity entityToSpawn = new ItemEntity(target.getLevel(), target.getX(), target.getY(), target.getZ(),
                        new ItemStack(projectile, target.getArrowCount()));
                entityToSpawn.setPickUpDelay(10);
                world.addFreshEntity(entityToSpawn);
            }
        }
    }
}
