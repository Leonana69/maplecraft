package net.maplecraft.utils;

import net.maplecraft.init.ItemsInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;

public class WeaponCrossbowItem extends WeaponItem {
    public boolean setUsingAnime = false;
    /* typical projectile damage is proportional to power * damage */
    // affect projectile damage, here we use BaseEquipItem.baseStats.values.get(1) // attack
    // public float damage = 5.0F;
    // affect projectile damage and speed
    public static float power = 4.0F;
    // affect accuracy, 0.0F means precise
    public static float accuracy = 0.2F;

    private boolean startSoundPlayed = false;
    private boolean midLoadSoundPlayed = false;
    private ItemStack chargedProjectileStack;

    public WeaponCrossbowItem(EquipBaseData data) {
        super(data.category(EquipCategory.CROSSBOW));
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
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (isCharged(itemStack)) {
            performShooting(world, player, hand, itemStack);
            setCharged(itemStack, false);
            return InteractionResultHolder.consume(itemStack);
        } else if (player.getAbilities().instabuild || !WeaponBowItem.findAmmo(player).isEmpty()) {
            this.startSoundPlayed = false;
            this.midLoadSoundPlayed = false;
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemStack);
        } else {
            return InteractionResultHolder.fail(itemStack);
        }
    }

    @Override
    public void onUseTick(Level world, LivingEntity livingEntity, ItemStack itemStack, int timeLeft) {
        if (!world.isClientSide) {
            SoundEvent soundevent = SoundEvents.CROSSBOW_QUICK_CHARGE_1;
            SoundEvent soundevent1 = SoundEvents.CROSSBOW_LOADING_MIDDLE;
            float f = (float)(itemStack.getUseDuration() - timeLeft) / (float)getChargeDuration(itemStack);
            if (f < 0.2F) {
                this.startSoundPlayed = false;
                this.midLoadSoundPlayed = false;
            }

            if (f >= 0.2F && !this.startSoundPlayed) {
                this.startSoundPlayed = true;
                world.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), soundevent, SoundSource.PLAYERS, 0.5F, 1.0F);
            }

            if (f >= 0.5F && !this.midLoadSoundPlayed) {
                this.midLoadSoundPlayed = true;
                world.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), soundevent1, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
        }
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level world, LivingEntity livingEntity, int timeLeft) {
        int i = this.getUseDuration(itemStack) - timeLeft;
        float f = i / (float) getChargeDuration(itemStack);
        if (f >= 1.0F && !isCharged(itemStack) && livingEntity instanceof Player player) {
            setCharged(itemStack, true);
            ItemStack ammoStack = WeaponBowItem.findAmmo(player);
            if (ammoStack.isEmpty()) {
                ammoStack = new ItemStack(ItemsInit.USE_ARROW_FOR_BOW.get());
            }
            chargedProjectileStack = ammoStack;
            SoundSource soundsource = SoundSource.PLAYERS;
            world.playSound((Player)null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.CROSSBOW_LOADING_END, soundsource, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        } else {
            chargedProjectileStack = null;
        }
    }

    public int getChargeDuration(ItemStack itemStack) {
        return 15 + this.baseEquipData.baseStats.get("ATTACK_SPEED") * 5;
    }

    private void performShooting(Level world, LivingEntity livingEntity, InteractionHand hand, ItemStack itemStack) {
        if (livingEntity instanceof Player player && net.minecraftforge.event.ForgeEventFactory.onArrowLoose(itemStack, player.level, player, 1, true) < 0) return;

        if (!world.isClientSide() && livingEntity instanceof ServerPlayer player && chargedProjectileStack != null) {
            MapleProjectileItem projectileItem = (MapleProjectileItem) chargedProjectileStack.getItem();
            AbstractArrow projectileEntity = projectileItem.createArrow(world, player);
            projectileEntity.shoot(player.getViewVector(1).x, player.getViewVector(1).y, player.getViewVector(1).z, power, accuracy);
            projectileEntity.setBaseDamage((player.getAttributeValue(ATTACK_DAMAGE) * 1.4 + projectileItem.bonusDamage) / power);
            projectileEntity.setKnockback(1);
            world.addFreshEntity(projectileEntity);
            itemStack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(player.getUsedItemHand()));
            if (!player.getAbilities().instabuild) {
                chargedProjectileStack.shrink(1);
                if (chargedProjectileStack.isEmpty()) {
                    player.getInventory().removeItem(chargedProjectileStack);
                }
            }
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                            Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_attack_bow"))),
                            SoundSource.PLAYERS, 1, 1);
        }
    }

    public static boolean isCharged(ItemStack itemStack) {
        CompoundTag compoundTag = itemStack.getTag();
        return compoundTag != null && compoundTag.getBoolean("Charged");
    }

    public static void setCharged(ItemStack itemStack, boolean isCharged) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        compoundTag.putBoolean("Charged", isCharged);
    }
}

