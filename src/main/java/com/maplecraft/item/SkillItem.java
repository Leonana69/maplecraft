package com.maplecraft.item;

import com.maplecraft.network.Variables;
import com.maplecraft.procedures.SkillDamageHandler;
import com.maplecraft.MapleCraftMod;
import com.maplecraft.entity.MapleProjectileEntity;
import com.maplecraft.entity.MapleSummonEntity;
import com.maplecraft.init.EffectsInit;
import com.maplecraft.init.ItemsInit;
import com.maplecraft.item.skill.SkillShadowPartner;
import com.maplecraft.network.SkillEffectMessageHandler;
import com.maplecraft.utils.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

import static com.maplecraft.utils.AllSkillKeyValues.SHADOW_PARTNER;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;

public class SkillItem extends Item {
    public String itemName;
    public SkillBaseData skillBaseData;
    public SkillEffectInstance hitEffect;
    public boolean consumeProjectile = false;
    public byte projectilePierceLevel = 0;
    public ItemStack projectile = ItemStack.EMPTY;

    public SkillItem(String itemName, SkillBaseData data, SkillEffectInstance hitEffect) {
        super(new Properties().stacksTo(1));
        this.itemName = itemName;
        this.skillBaseData = data;
        this.hitEffect = hitEffect;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemStack, world, list, flag);

        list.add(Component.literal(
                Component.translatable("utils.maplecraft.skill_job_require").getString()
                        + skillBaseData.jobReq.typeName
        ));

        list.add(Component.translatable("utils.maplecraft.base_equip_item_divider"));

        String s = "";
        if (skillBaseData.damage > 0) {
            s += Component.translatable("utils.maplecraft.skill_damage").getString() + skillBaseData.damage + "%";
            if (skillBaseData.attackCount > 1) {
                s += "*" + skillBaseData.attackCount;
            }
            s += ", ";
        }
        s += Component.translatable("utils.maplecraft.skill_mana_cost").getString()
                + skillBaseData.manaCost;
        if (skillBaseData.healthCost > 0) {
            s += Component.translatable("utils.maplecraft.skill_health_cost").getString()
                    + skillBaseData.healthCost;
        }
        s += Component.translatable("utils.maplecraft.skill_cooldown").getString() + skillBaseData.cooldown + "s";
        list.add(Component.literal(s));

        s = "item.maplecraft." + itemName + "_description";
        list.add(Component.translatable(s));
    }

    public void skillEffect(Player player) {}
    public void onHitEffect(Player player, LivingEntity entity) {}
    public boolean setProjectile(Player player) {
        projectile = ItemStack.EMPTY;
        if (skillBaseData.weaponReq.contains(EquipCategory.CLAW)) {
            projectile = WeaponClawItem.findAmmo(player);
        } else if (skillBaseData.weaponReq.contains(EquipCategory.BOW)
                || skillBaseData.weaponReq.contains(EquipCategory.CROSSBOW)) {
            projectile = WeaponBowItem.findAmmo(player);
        } else {
            return false;
        }

        // even with soul arrow, you need at least one arrow
        if (!projectile.isEmpty() || player.getAbilities().instabuild) {
            if (projectile.isEmpty()) {
                if (skillBaseData.weaponReq.contains(EquipCategory.CLAW)) {
                    projectile = new ItemStack(ItemsInit.UES_SUBI_THROWING_STARS.get());
                } else if (skillBaseData.weaponReq.contains(EquipCategory.BOW)
                        || skillBaseData.weaponReq.contains(EquipCategory.CROSSBOW)) {
                    projectile = new ItemStack(ItemsInit.USE_ARROW_FOR_BOW.get());
                }
            }
            return player.getAbilities().instabuild
                    || projectile.getCount() >= skillBaseData.attackCount
                    || player.getEffect(EffectsInit.BUFF_SOUL_ARROW.get()) != null;
        }

        return false;
    }

    public boolean canUse(Player player) {
        double mana = (double) Variables.get(player, "playerManaPoints");
        JobCategory job = JobCategory.getJob((int) Variables.get(player, "jobType"));

        ItemStack mainHandItemStack = player.getMainHandItem();
        EquipCategory weapon = EquipCategory.NONE;
        if (mainHandItemStack.getItem() instanceof IBaseEquip equip) {
            weapon = equip.getBaseEquipData().category;
        }

        return (this.skillBaseData.weaponReq.contains(EquipCategory.NONE)
                || this.skillBaseData.weaponReq.contains(weapon))
                && mana >= this.skillBaseData.manaCost
                && this.skillBaseData.jobReq.isSuccessor(job)
                && (!consumeProjectile || setProjectile(player));
    }

    public String getSKillSound() {
        return "maplecraft:sound_" + itemName;
    }

    public void playerEffect(Player player) {
        // cost mana, health, and damage item
        if (!player.getAbilities().instabuild) {
            double mana = (double) Variables.get(player, "playerManaPoints");
            Variables.set(player, "playerManaPoints", mana - this.skillBaseData.manaCost);

            float health = player.getHealth();
            player.setHealth(health - (float) this.skillBaseData.healthCost);

            ItemStack weapon = player.getMainHandItem();
            if (weapon.getItem() instanceof WeaponItem) {
                weapon.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(player.getUsedItemHand()));
            }
        }

        player.swing(InteractionHand.MAIN_HAND, true);
        if (player.level instanceof ServerLevel serverLevel)
            serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(),
                    Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(getSKillSound()))),
                    SoundSource.PLAYERS, 1, 1);
    }

    public void scheduleDamage(Player player, List<LivingEntity> list, float amplifier) {
        for (LivingEntity livingEntity : list) {
            int randomDelay = (int) (player.getRandom().nextFloat() * 3);
            SkillDamageHandler.damageQueue.add(new SkillDamageInstance(
                    this.skillBaseData.skillId,
                    this.getSkillDamage(player) * amplifier,
                    this.skillBaseData.attackCount,
                    player.tickCount + this.skillBaseData.delay + randomDelay,
                    this.skillBaseData.attackInterval,
                    livingEntity
            ));
            if (!this.hitEffect.hitEffectOnHit)
                scheduleHitEffect(player, livingEntity);
        }
    }

    public void scheduleDamage(Player player, List<LivingEntity> list) {
        scheduleDamage(player, list, 1F);
    }

    public void scheduleHitEffect(Player player, LivingEntity target) {
        if (this.hitEffect.animeCount > 0 && player instanceof ServerPlayer serverPlayer) {
            SkillEffectInstance s = new SkillEffectInstance(this.hitEffect);
            s.renderPos = new Vec3(target.getX(), target.getY(), target.getZ());
            MapleCraftMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new SkillEffectMessageHandler(s));
        }
    }

    public MapleProjectileEntity createArrow(Player player) {
        return null;
    }

    public void scheduleProjectile(Player player, List<LivingEntity> list) {
        Vec3 direction = player.getViewVector(1);
        boolean hasSoulArrow = player.getEffect(EffectsInit.BUFF_SOUL_ARROW.get()) != null;
        boolean hasShadowPartner = player.getEffect(EffectsInit.BUFF_SHADOW_PARTNER.get()) != null;
        boolean isShadowPartner = this instanceof SkillShadowPartner;

        for (int i = 0; i < this.skillBaseData.attackCount; i++) {
            MapleProjectileEntity projectileEntity = null;
            if (this.projectile.getItem() instanceof MapleProjectileItem item) {
                projectileEntity = item.createArrow(player.level, player);
            }

            MapleProjectileEntity magicArrow = createArrow(player);
            if (magicArrow != null) {
                projectileEntity = magicArrow;
            }

            if (projectileEntity == null) {
                return;
            }

            setProjectileData(player, projectileEntity);

            if (!list.isEmpty())
                projectileEntity.target = list.get(0);
            if (hasSoulArrow || isShadowPartner)
                projectileEntity.setCanNotPickUp();

            long delay = player.tickCount
                    + this.skillBaseData.delay
                    + (long) this.skillBaseData.attackInterval * i;
            SkillDamageHandler.projectileQueue.add(new SkillProjectileInstance(
                    this.skillBaseData.skillId,
                    projectileEntity,
                    direction,
                    delay
            ));

            if (hasShadowPartner && !isShadowPartner) {
                MapleProjectileEntity projectileEntityShadow = null;
                if (magicArrow != null)
                    projectileEntityShadow = createArrow(player);
                else
                    projectileEntityShadow = ((MapleProjectileItem) this.projectile.getItem()).createArrow(player.level, player);

                setProjectileData(player, projectileEntityShadow);
                if (!list.isEmpty())
                    projectileEntityShadow.target = list.get(0);
                projectileEntityShadow.setBaseDamage(projectileEntityShadow.getBaseDamage() * SHADOW_PARTNER.damage / 100D);
                projectileEntityShadow.setCanNotPickUp();
                SkillDamageHandler.projectileQueue.add(new SkillProjectileInstance(
                        this.skillBaseData.skillId,
                        projectileEntityShadow,
                        direction,
                        delay
                                + 4 // extra delay for shadow partner skills
                                + (long) this.skillBaseData.attackInterval * (this.skillBaseData.attackCount - 1)
                ));
            }
        }

        if (!hasSoulArrow && !isShadowPartner && !player.getAbilities().instabuild) {
            projectile.shrink(skillBaseData.attackCount);
            if (projectile.isEmpty()) {
                player.getInventory().removeItem(projectile);
            }
        }
    }

    private void setProjectileData(Player player, MapleProjectileEntity projectile) {
        projectile.setPierceLevel(projectilePierceLevel);

        if (skillBaseData.weaponReq.contains(EquipCategory.BOW)) {
            projectile.power = WeaponBowItem.power;
        } else if (skillBaseData.weaponReq.contains(EquipCategory.CLAW)) {
            projectile.power = WeaponClawItem.power;
        } else if (skillBaseData.weaponReq.contains(EquipCategory.CROSSBOW)) {
            projectile.power = WeaponCrossbowItem.power;
        } else {
            projectile.power = 1.0F;
        }

        projectile.skillId = skillBaseData.skillId;
        projectile.setBaseDamage(getSkillDamage(player) / projectile.power);
    }

    public void generateProjectile(Player player, SkillProjectileInstance instance) {
        MapleProjectileEntity projectileEntity = instance.entity;
        Vec3 dir = instance.shootDirection.add(
                player.getRandom().nextDouble() / 15,
                player.getRandom().nextDouble() / 15,
                player.getRandom().nextDouble() / 15).normalize();
        projectileEntity.shoot(dir.x, dir.y, dir.z, projectileEntity.power, projectileEntity.accuracy);
        player.level.addFreshEntity(projectileEntity);
        if (skillBaseData.weaponReq.contains(EquipCategory.BOW)
            || skillBaseData.weaponReq.contains(EquipCategory.CROSSBOW)) {
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_attack_bow"))),
                    SoundSource.PLAYERS, 1, 1);
        } else if (skillBaseData.weaponReq.contains(EquipCategory.CLAW)) {
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_attack_claw"))),
                    SoundSource.PLAYERS, 1, 1);
        }
    }

    public float getSkillDamage(Player player) {
        float value;
        if (this.skillBaseData.isMagic)
            value = (float) (double) Variables.get(player, "mAttackBoost") * 1.2F;
        else {
            float bonusDamage = 0F;
            if (this.consumeProjectile && this.projectile.getItem() instanceof MapleProjectileItem item) {
                bonusDamage = item.bonusDamage;
            }
            value = (float) (player.getAttributeValue(ATTACK_DAMAGE) + bonusDamage);
        }

        return value * skillBaseData.damage / 100.0F;
    }

    public void dealDamage(Player player, SkillDamageInstance instance) {
        if (instance.target != null) {
            LivingEntity livingEntity = instance.target;
            if (this.hitEffect.hitEffectOnHit && instance.attackCount == instance.maxAttackCount) {
                scheduleHitEffect(player, instance.target);
                // knock-back only for the first attack
                livingEntity.knockback(0.2D, Mth.sin(player.getYRot() * ((float)Math.PI / 180F)), -Mth.cos(player.getYRot() * ((float)Math.PI / 180F)));
            }
            livingEntity.invulnerableTime = 0;
            livingEntity.hurt(DamageSource.playerAttack(player), instance.attackDamage);
            onHitEffect(player, livingEntity);
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_mob_damage"))),
                    SoundSource.PLAYERS, 1, 1);
        }
    }

    public static List<LivingEntity> getEntitiesInFrontOfPlayer(Player player, double radius, double distance) {
        return getEntitiesInFrontOfPlayer(player, radius, distance, false);
    }

    public static List<LivingEntity> getEntitiesInFrontOfPlayer(Player player, double radius, double distance, boolean checkBack) {
        List<LivingEntity> list = new ArrayList<>();
        Vec3 view = player.getViewVector(0);
        Vec3 base = player.getPosition(0);

        double startX = 0.0;
        if (checkBack)
            startX = radius / 2;

        for (double i = 0; i <= distance; i += 1) {
            base = base.add(view.scale(1));
            AABB box = new AABB(
                    base.x - startX,
                    base.y - 1,
                    base.z - radius / 2,
                    base.x + radius / 2,
                    base.y + radius - 1,
                    base.z + radius / 2);
            list.addAll(player.level.getEntitiesOfClass(LivingEntity.class, box));
        }

        Set<LivingEntity> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Player || list.get(i) instanceof MapleSummonEntity) {
                list.remove(i);
                i--;
            }
        }

        return list;
    }

    public static List<LivingEntity> getClosestEntity(Player player, double radius, double distance) {
        return getClosestEntity(player, radius, distance, false);
    }

    public static List<LivingEntity> getClosestEntity(Player player, double radius, double distance, boolean checkBack) {
        List<LivingEntity> list = getEntitiesInFrontOfPlayer(player, radius, distance, checkBack);
        List<LivingEntity> target = new ArrayList<>();
        if (!list.isEmpty()) {
            double minDis = 99999;
            int index = 0;
            for (int i = 0; i < list.size(); i++) {
                double dis = player.distanceTo(list.get(i));
                if (dis < minDis) {
                    minDis = dis;
                    index = i;
                }
            }

            target.add(list.get(index));
        }
        return target;
    }

    public static List<LivingEntity> getUndeadEntity(List<LivingEntity> list) {
        List<LivingEntity> targets = new ArrayList<>();
        for (LivingEntity livingEntity : list) {
            if (livingEntity instanceof Monster monster && monster.getMobType() == MobType.UNDEAD) {
                targets.add(livingEntity);
            }
        }
        return targets;
    }

    public static List<LivingEntity> getLivingEntity(List<LivingEntity> list) {
        List<LivingEntity> targets = new ArrayList<>();
        for (LivingEntity livingEntity : list) {
            if (!(livingEntity instanceof Monster) || livingEntity.getMobType() != MobType.UNDEAD) {
                targets.add(livingEntity);
            }
        }
        return targets;
    }
}
