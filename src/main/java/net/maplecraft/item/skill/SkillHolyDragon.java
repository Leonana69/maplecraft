package net.maplecraft.item.skill;

import net.maplecraft.effect.buffEffect.BuffHolyDragonMobEffect;
import net.maplecraft.entity.summon.holyDragon.HolyDragonEntity;
import net.maplecraft.init.EffectsInit;
import net.maplecraft.init.EntitiesInit;
import net.maplecraft.item.SkillItem;
import net.maplecraft.utils.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import static net.maplecraft.entity.boss.zakum.BossZakumSpawnEggItem.customSpawn;
import static net.maplecraft.utils.AllSkillKeyValues.HOLY_DRAGON;

public class SkillHolyDragon extends SkillItem {
    public static String itemName = "skill_holy_dragon";
    public SkillHolyDragon() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.PRIEST)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(HOLY_DRAGON.skillID)
                        .setDamage(HOLY_DRAGON.damage)
                        .setAttackCount(HOLY_DRAGON.attackCount)
                        .setManaCost(HOLY_DRAGON.manaCost)
                        .setIsMagic(true),
                new SkillEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        HolyDragonEntity entity = (HolyDragonEntity) customSpawn(EntitiesInit.HOLY_DRAGON_ENTITY.get(),
                (ServerLevel) player.level, player,
                player.position().add(1, 1, 1),
                MobSpawnType.SPAWNER);

        if (entity != null) {
            entity.tame(player);
            entity.setLifeTime(120 * 20);
            entity.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(getSkillDamage(player));
            MobEffectInstance instance = new MobEffectInstance(
                    EffectsInit.BUFF_HOLY_DRAGON.get(),
                    120 * 20, // duration in tick
                    0,
                    false, true);
            if (instance.getEffect() instanceof BuffHolyDragonMobEffect effect) {
                effect.setSummonedEntity(entity);
            }
            player.addEffect(instance);
        }
    }
}
