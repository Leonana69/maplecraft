package net.maplecraft.utils;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.init.TabsInit;
import net.maplecraft.item.UseBalancedFuryItem;
import net.maplecraft.item.UseSteelyThrowingKnivesItem;
import net.maplecraft.item.UseSubiThrowingStarsItem;
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
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;

public class WeaponClawItem extends WeaponItem {
    /* typical projectile damage is proportional to power * damage */
    // affect projectile damage, here we use BaseEquipItem.baseStats.values.get(1) // attack
    // public float damage = 5.0F;
    // affect projectile damage and speed
    public float power = 2.0F;
    // affect accuracy, 0.0F means precise
    public float accuracy = 2.0F;

    public WeaponClawItem(Properties properties, EquipBaseData data) {
        super(properties.tab(TabsInit.TAB_MAPLE_CRAFT), data.category(EquipCategory.CLAW));
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemstack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack itemstack) {
        return 72000;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, Player entity, @NotNull InteractionHand hand) {
        entity.startUsingItem(hand);
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, entity.getItemInHand(hand));
    }

    @Override
    public void releaseUsing(@NotNull ItemStack itemstack, Level world, @NotNull LivingEntity entityLiving, int timeLeft) {
        if (!world.isClientSide() && entityLiving instanceof ServerPlayer player) {
            ItemStack ammoStack = this.findAmmo(player);

            int duration = this.getUseDuration(itemstack) - timeLeft;
            float powerScale = getPowerForTime(duration);

            if (!ammoStack.isEmpty() || player.getAbilities().instabuild) {
                if (ammoStack.isEmpty()) {
                    ammoStack = new ItemStack(ItemsInit.UES_SUBI_THROWING_STARS.get());
                }

                MapleProjectileItem ammoItem = (MapleProjectileItem) ammoStack.getItem();
                AbstractArrow ammoEntity = ammoItem.createArrow(world, player);

                ammoEntity.shoot(player.getViewVector(1).x, player.getViewVector(1).y, player.getViewVector(1).z, power, accuracy);

                double damage = (player.getAttributeValue(ATTACK_DAMAGE) + ammoItem.bonusDamage) / power;
                ammoEntity.setBaseDamage(damage * powerScale);
                world.addFreshEntity(ammoEntity);

                itemstack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(player.getUsedItemHand()));

                if (!player.getAbilities().instabuild) {
                    ammoStack.shrink(1);
                    if (ammoStack.isEmpty()) {
                        player.getInventory().removeItem(ammoStack);
                    }
                }

                world.playSound(null, player.getX(), player.getY(), player.getZ(),
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

    public static float getPowerForTime(int time) {
        float f = (float)time / 6.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        return Math.min(f, 1.0F);
    }
}
