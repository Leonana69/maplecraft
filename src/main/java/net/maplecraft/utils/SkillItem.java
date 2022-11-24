package net.maplecraft.utils;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.init.TabsInit;
import net.maplecraft.network.Variables;
import net.maplecraft.procedures.DelayedDamageHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
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
    public ItemLike projectile = null;

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
        ItemStack ammoStack = ItemStack.EMPTY;
        if (skillBaseData.weaponReq == EquipCategory.CLAW) {
            ammoStack = WeaponClawItem.findAmmo(player);
        } else if (skillBaseData.weaponReq == EquipCategory.BOW) {
            ammoStack = WeaponBowItem.findAmmo(player);
        } else {
            return false;
        }

        if (!ammoStack.isEmpty() || player.getAbilities().instabuild) {
            if (ammoStack.isEmpty()) {
                if (skillBaseData.weaponReq == EquipCategory.CLAW) {
                    ammoStack = new ItemStack(ItemsInit.UES_SUBI_THROWING_STARS.get());
                } else if (skillBaseData.weaponReq == EquipCategory.BOW) {
                    ammoStack = new ItemStack(Items.ARROW);
                }
            }
            projectile = ammoStack.getItem();
            return true;
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

    public void playerEffect(Player player) {
        // cost mana
        if (!player.level.isClientSide && !player.getAbilities().instabuild) {
            double mana = (double) Variables.get(player, "playerManaPoints");
            Variables.set(player, "playerManaPoints", mana - this.skillBaseData.manaCost);

            float health = player.getHealth();
            player.setHealth(health - (float) this.skillBaseData.healthCost);
        }

        player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_" + itemName))),
                SoundSource.PLAYERS, 1, 1);
    }

    public void scheduleDamage(Player player, List<LivingEntity> list) {
        DelayedDamageHandler.damageQueue.add(new SkillDamageInstance(
                this.skillBaseData.skillID,
                this.skillBaseData.attackCount,
                player.level.getGameTime() + this.skillBaseData.delay,
                this.skillBaseData.delay,
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

    }

    public void dealDamage(Player player, SkillDamageInstance instance) {
        float value;
        if (this.skillBaseData.isMagic)
            value = (float) (double) Variables.get(player, "mAttackBoost") * skillBaseData.damage / 100.0F * 1.2F;
        else
            value = (float) player.getAttributeValue(ATTACK_DAMAGE) * skillBaseData.damage / 100.0F;

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
