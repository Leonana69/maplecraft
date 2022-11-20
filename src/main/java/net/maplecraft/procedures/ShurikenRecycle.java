package net.maplecraft.procedures;

import net.maplecraft.entities.BalancedFuryEntity;
import net.maplecraft.entities.SteelyThrowingKnivesEntity;
import net.maplecraft.entities.SubiThrowingStarsEntity;
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
public class ShurikenRecycle {
    @SubscribeEvent
    public static void onLivingEntityDied(LivingDeathEvent event) {
        LivingEntity target = event.getEntity();
        Level world = target.getLevel();
        if (!world.isClientSide) {
            Entity entity = event.getSource().getDirectEntity();
            ItemLike shuriken = null;

            if (entity instanceof SubiThrowingStarsEntity) {
                shuriken = ItemsInit.UES_SUBI_THROWING_STARS.get();
            } else if (entity instanceof SteelyThrowingKnivesEntity) {
                shuriken = ItemsInit.USE_STEELY_THROWING_KNIVES.get();
            } else if (entity instanceof BalancedFuryEntity) {
                shuriken = ItemsInit.USE_BALANCED_FURY.get();
            }

            if (shuriken != null) {
                ItemEntity entityToSpawn = new ItemEntity(target.getLevel(), target.getX(), target.getY(), target.getZ(),
                        new ItemStack(shuriken, target.getArrowCount()));
                entityToSpawn.setPickUpDelay(10);
                world.addFreshEntity(entityToSpawn);
            }
        }
    }
}
