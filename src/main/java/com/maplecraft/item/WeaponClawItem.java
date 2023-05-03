package com.maplecraft.item;

import com.maplecraft.item.use.UseBalancedFuryItem;
import com.maplecraft.item.use.UseSteelyThrowingKnivesItem;
import com.maplecraft.item.use.UseSubiThrowingStarsItem;
import com.maplecraft.utils.EquipBaseData;
import com.maplecraft.init.EffectsInit;
import com.maplecraft.init.ItemsInit;
import com.maplecraft.item.use.UseIcicleItem;
import com.maplecraft.utils.EquipCategory;
import com.maplecraft.entity.MapleProjectileEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;

public class WeaponClawItem extends WeaponItem {
    /* typical projectile damage is proportional to power * damage */
    // affect projectile damage, here we use BaseEquipItem.baseStats.values.get(1) // attack
    // public float damage = 5.0F;
    // affect projectile damage and speed
    public static float power = 2.0F;
    // affect accuracy, 0.0F means precise
    public static float accuracy = 1.0F;

    public WeaponClawItem(EquipBaseData data) {
        super(data.category(EquipCategory.CLAW));
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
    public void releaseUsing(@NotNull ItemStack itemStack, Level world, @NotNull LivingEntity livingEntity, int timeLeft) {
        if (!world.isClientSide() && livingEntity instanceof ServerPlayer player) {
            ItemStack ammoStack = findAmmo(player);

            int duration = this.getUseDuration(itemStack) - timeLeft;
            float powerScale = getPowerForTime(duration);

            if (!ammoStack.isEmpty() || player.getAbilities().instabuild) {
                if (ammoStack.isEmpty()) {
                    ammoStack = new ItemStack(ItemsInit.UES_SUBI_THROWING_STARS.get());
                }

                MapleProjectileItem ammoItem = (MapleProjectileItem) ammoStack.getItem();
                MapleProjectileEntity ammoEntity = ammoItem.createArrow(world, player);

                boolean shadowPartner = player.getEffect(EffectsInit.BUFF_SHADOW_PARTNER.get()) != null;
                if (shadowPartner) {
                    SkillItem skill = (SkillItem) ItemsInit.SKILL_SHADOW_PARTNER.get();
                    skill.scheduleProjectile(player, new ArrayList<>());
                }

                ammoEntity.shoot(player.getViewVector(1).x, player.getViewVector(1).y, player.getViewVector(1).z, power, accuracy);

                double damage = (player.getAttributeValue(ATTACK_DAMAGE) + ammoItem.bonusDamage) / power;

                ammoEntity.setBaseDamage(damage * powerScale);
                world.addFreshEntity(ammoEntity);

                itemStack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(player.getUsedItemHand()));

                if (!player.getAbilities().instabuild) {
                    ammoStack.shrink(1);
                    if (ammoStack.isEmpty()) {
                        player.getInventory().removeItem(ammoStack);
                    }
                }

                world.playSound(null, player.getX(), player.getY(), player.getZ(),
                        Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_attack_claw"))),
                        SoundSource.PLAYERS, 1, 1);
            }
        }
    }

    public static ItemStack findAmmo(Player player) {
        if (isValidProjectile(player.getItemInHand(InteractionHand.OFF_HAND).getItem())) {
            return player.getItemInHand(InteractionHand.OFF_HAND);
        } else {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack itemStack = player.getInventory().getItem(i);
                if (isValidProjectile(itemStack.getItem())) {
                    return itemStack;
                }
            }
            return ItemStack.EMPTY;
        }
    }

    public static boolean isValidProjectile(Item item) {
        return item instanceof UseSubiThrowingStarsItem
                || item instanceof UseIcicleItem
                || item instanceof UseSteelyThrowingKnivesItem
                || item instanceof UseBalancedFuryItem;
    }

    public static float getPowerForTime(int time) {
        float f = (float)time / 6.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        return Math.min(f, 1.0F);
    }
}
