package net.maplecraft.utils;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.init.TabsInit;
import net.maplecraft.items.UseBalancedFuryItem;
import net.maplecraft.items.UseSteelyThrowingKnivesItem;
import net.maplecraft.items.UseSubiThrowingStarsItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.Objects;

public class ClawWeaponItem extends WeaponItem {
    /* typical projectile damage is proportional to power * damage */
    // affect projectile damage, here we use BaseEquipItem.baseStats.values.get(1) // attack
    // public float damage = 5.0F;
    // affect projectile damage and speed
    public float power = 2.0F;
    // affect accuracy, 0.0F means precise
    public float accuracy = 2.0F;

    public ClawWeaponItem(Properties properties, BonusStats bs) {
        super(properties.tab(TabsInit.TAB_MAPLE_CRAFT), EquipCategory.CLAW, bs);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        entity.startUsingItem(hand);
        return new InteractionResultHolder(InteractionResult.SUCCESS, entity.getItemInHand(hand));
    }

    @Override
    public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entityLiving, int timeLeft) {
        if (!world.isClientSide() && entityLiving instanceof ServerPlayer entity) {
            ItemStack ammoStack = this.findAmmo(entity);

            if (!ammoStack.isEmpty() || entity.getAbilities().instabuild) {
                if (ammoStack.isEmpty()) {
                    ammoStack = new ItemStack(ItemsInit.UES_SUBI_THROWING_STARS.get());
                }

                MapleProjectileItem ammoItem = (MapleProjectileItem) ammoStack.getItem();
                AbstractArrow ammoEntity = ammoItem.createArrow(world, entity);

                ammoEntity.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power, accuracy);
                ammoEntity.setBaseDamage(this.equipData.baseStats.get("ATTACK") + ammoItem.bonusDamage / power);

                ammoEntity.setKnockback(1);

                world.addFreshEntity(ammoEntity);

                itemstack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(entity.getUsedItemHand()));

                if (!entity.getAbilities().instabuild) {
                    ammoStack.shrink(1);
                    if (ammoStack.isEmpty()) {
                        entity.getInventory().removeItem(ammoStack);
                    }
                }

                world.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                        Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_claw_attack"))),
                        SoundSource.PLAYERS, 1, 1);
            }
        }
    }

    protected ItemStack findAmmo(Player player) {
        if (isValidProjectile(player.getItemInHand(InteractionHand.OFF_HAND).getItem())) {
            return player.getItemInHand(InteractionHand.OFF_HAND);
        } else if (isValidProjectile(player.getItemInHand(InteractionHand.MAIN_HAND).getItem())) {
            return player.getItemInHand(InteractionHand.MAIN_HAND);
        } else {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack itemstack = player.getInventory().getItem(i);
                if (isValidProjectile(itemstack.getItem())) {
                    return itemstack;
                }
            }
            return ItemStack.EMPTY;
        }
    }

    public boolean isValidProjectile(Item item) {
        return item instanceof UseSubiThrowingStarsItem || item instanceof UseSteelyThrowingKnivesItem || item instanceof UseBalancedFuryItem;
    }
}

