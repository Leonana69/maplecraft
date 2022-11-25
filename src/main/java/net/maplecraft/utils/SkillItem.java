package net.maplecraft.utils;

import net.maplecraft.entities.BombArrowEntity;
import net.maplecraft.init.ItemsInit;
import net.maplecraft.init.TabsInit;
import net.maplecraft.item.skill.SkillArrowBomb;
import net.maplecraft.network.Variables;
import net.maplecraft.procedures.DelayedDamageHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;

public class SkillItem extends Item {
    public String itemName;
    public SkillBaseData skillBaseData;
    public SkillHitEffectInstance hitEffect;
    public boolean consumeProjectile = false;
    public ItemStack projectile = null;

    public SkillItem(String itemName, SkillBaseData data, SkillHitEffectInstance hitEffect) {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT).stacksTo(1));
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
        list.add(Component.literal(s));

        s = "item.maplecraft." + itemName + "_description";
        list.add(Component.translatable(s));
    }

    public void skillEffect(Player player) {}
    public void onHitEffect(LivingEntity entity) {}
    public boolean setProjectile(Player player) {
        projectile = ItemStack.EMPTY;
        if (skillBaseData.weaponReq == EquipCategory.CLAW) {
            projectile = WeaponClawItem.findAmmo(player);
        } else if (skillBaseData.weaponReq == EquipCategory.BOW) {
            projectile = WeaponBowItem.findAmmo(player);
        } else {
            return false;
        }

        if (!projectile.isEmpty() || player.getAbilities().instabuild) {
            if (projectile.isEmpty()) {
                if (skillBaseData.weaponReq == EquipCategory.CLAW) {
                    projectile = new ItemStack(ItemsInit.UES_SUBI_THROWING_STARS.get());
                } else if (skillBaseData.weaponReq == EquipCategory.BOW) {
                    projectile = new ItemStack(ItemsInit.USE_ARROW_FOR_BOW.get());
                }
            }
            return player.getAbilities().instabuild || projectile.getCount() >= skillBaseData.attackCount;
        }

        return false;
    }

    public boolean canUse(Player player) {
        double mana = (double) Variables.get(player, "playerManaPoints");
        JobCategory job = JobCategory.getJob((int) Variables.get(player, "jobType"));

        ItemStack mainHandItemStack = player.getMainHandItem();
        EquipCategory weapon = EquipCategory.NONE;
        if (mainHandItemStack.getItem() instanceof IBaseEquip equip) {
            weapon = equip.getCategory();
        }

        return (this.skillBaseData.weaponReq.type == -1 || weapon == this.skillBaseData.weaponReq)
                && mana >= this.skillBaseData.manaCost
                && this.skillBaseData.jobReq.isSuccessor(job)
                && (!consumeProjectile || setProjectile(player));
    }

    public String getSKillSound() {
        return "maplecraft:sound_" + itemName;
    }

    public void playerEffect(Player player) {
        // cost mana
        if (!player.level.isClientSide && !player.getAbilities().instabuild) {
            double mana = (double) Variables.get(player, "playerManaPoints");
            Variables.set(player, "playerManaPoints", mana - this.skillBaseData.manaCost);

            float health = player.getHealth();
            player.setHealth(health - (float) this.skillBaseData.healthCost);
            if (consumeProjectile) {
                projectile.shrink(skillBaseData.attackCount);
                if (projectile.isEmpty()) {
                    player.getInventory().removeItem(projectile);
                }
            }
        }

        player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(getSKillSound()))),
                SoundSource.PLAYERS, 1, 1);
    }

    public void scheduleDamage(Player player, List<LivingEntity> list) {
        DelayedDamageHandler.damageQueue.add(new SkillDamageInstance(
                this.skillBaseData.skillID,
                this.skillBaseData.attackCount,
                player.level.getGameTime() + this.skillBaseData.delay,
                this.skillBaseData.attackInterval,
                list
        ));

        if (!hitEffect.hitEffectOnHit)
            scheduleHitEffect(player, list);
    }
    public void scheduleHitEffect(Player player, List<LivingEntity> list) {
        if (this.hitEffect.animeCount > 0) {
            SkillHitEffectInstance s = new SkillHitEffectInstance(this.hitEffect);

            s.tick = player.level.getGameTime();
            s.targets = list;
            DelayedDamageHandler.hitEffectQueue.add(s);
        }
    }

    public void scheduleProjectile(Player player, List<LivingEntity> list) {
        Vec3 direction = player.getViewVector(1);
        for (int i = 0; i < this.skillBaseData.attackCount; i++) {
            MapleProjectileEntity ammoEntity = null;
            double bonusDamage;
            if (this.projectile.getItem() instanceof MapleProjectileItem item) {
                ammoEntity = (MapleProjectileEntity) item.createArrow(player.level, player);
                bonusDamage = item.bonusDamage;
                if (this instanceof SkillArrowBomb arrow) {
                    ammoEntity = new BombArrowEntity(player.level, player);
                }
            } else {
                return;
            }

            if (!list.isEmpty())
                ammoEntity.target = list.get(0);
            if (skillBaseData.weaponReq == EquipCategory.BOW) {
                ammoEntity.power = WeaponBowItem.power;
            } else if (skillBaseData.weaponReq == EquipCategory.CLAW) {
                ammoEntity.power = WeaponClawItem.power;
            }

            ammoEntity.skillID = skillBaseData.skillID;

            double damage = (player.getAttributeValue(ATTACK_DAMAGE) + bonusDamage) / ammoEntity.power;
            ammoEntity.setBaseDamage(damage * skillBaseData.damage / 100.0D);

            DelayedDamageHandler.projectileQueue.add(new SkillProjectileInstance(
                    this.skillBaseData.skillID,
                    ammoEntity,
                    direction,
                    player.level.getGameTime()
                            + this.skillBaseData.delay
                            + (long) this.skillBaseData.attackInterval * i
            ));
        }
    }

    public void generateProjectile(Player player, SkillProjectileInstance instance) {
        MapleProjectileEntity entity = instance.entity;
        Vec3 dir = instance.shootDirection.add(
                player.getRandom().nextDouble() / 20,
                player.getRandom().nextDouble() / 20,
                player.getRandom().nextDouble() / 20).normalize();
        entity.shoot(dir.x, dir.y, dir.z, entity.power, entity.accuracy);
        player.level.addFreshEntity(entity);
        if (this.skillBaseData.weaponReq == EquipCategory.BOW) {
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_bow_attack"))),
                    SoundSource.PLAYERS, 1, 1);
        } else if (this.skillBaseData.weaponReq == EquipCategory.CLAW) {
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_claw_attack"))),
                    SoundSource.PLAYERS, 1, 1);
        }
    }

    public void dealDamage(Player player, SkillDamageInstance instance) {
        float value;
        if (this.skillBaseData.isMagic)
            value = (float) (double) Variables.get(player, "mAttackBoost") * skillBaseData.damage / 100.0F * 1.2F;
        else {
            float bonusDamage = 0F;
            if (this.consumeProjectile && this.projectile.getItem() instanceof MapleProjectileItem item) {
                bonusDamage = item.bonusDamage;
            }
            value = (float) (player.getAttributeValue(ATTACK_DAMAGE) + bonusDamage) * skillBaseData.damage / 100.0F;
        }

        if (hitEffect.hitEffectOnHit && instance.attackCount == instance.maxAttackCount)
            scheduleHitEffect(player, instance.targets);

        for (LivingEntity livingEntity : instance.targets) {
            livingEntity.invulnerableTime = 0;
            livingEntity.knockback(0.2D, Mth.sin(player.getYRot() * ((float)Math.PI / 180F)), -Mth.cos(player.getYRot() * ((float)Math.PI / 180F)));
            livingEntity.hurt(DamageSource.playerAttack(player), value);
            onHitEffect(livingEntity);
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
            if (list.get(i) instanceof Player) {
                list.remove(i);
                i--;
            }
        }

        for (LivingEntity livingEntity : list) {
            System.out.println(livingEntity.getName() + ", " + livingEntity.getHealth());
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
}
