package com.maplecraft.item.skill;

import com.maplecraft.init.EffectsInit;
import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.EquipCategory;
import com.maplecraft.utils.JobCategory;
import com.maplecraft.utils.SkillBaseData;
import com.maplecraft.utils.SkillEffectInstance;
import com.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static com.maplecraft.utils.AllSkillKeyValues.PANIC;

public class SkillPanic extends SkillItem {
    public static String itemName = "skill_panic";
    public SkillPanic() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.CRUSADER)
                        .setWeaponReq(EquipCategory.SWORD)
                        .setSkillID(PANIC.skillId)
                        .setDamage(PANIC.damage)
                        .setAttackCount(PANIC.attackCount)
                        .setManaCost(PANIC.manaCost)
                        .setCooldown(PANIC.cooldown),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(5)
                        .setTextureSize(185, 236));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, PANIC.radius, PANIC.distance);
        float amplifier = 1F;
        MobEffectInstance instance = player.getEffect(EffectsInit.BUFF_COMBO_ATTACK.get());
        if (instance != null) {
            amplifier += instance.getAmplifier() * 0.3;
            player.removeEffect(EffectsInit.BUFF_COMBO_ATTACK.get());
            player.addEffect(new MobEffectInstance(
                    EffectsInit.BUFF_COMBO_ATTACK.get(),
                    instance.getDuration(), // duration in tick
                    0,
                    false, true));
        }
        scheduleDamage(player, target, amplifier);
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 120, 1));
    }
}
