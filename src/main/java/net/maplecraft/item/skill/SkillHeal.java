package net.maplecraft.item.skill;

import com.mojang.math.Vector3f;
import net.maplecraft.item.SkillItem;
import net.maplecraft.utils.*;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.HEAL;

public class SkillHeal extends SkillItem {
    public static String itemName = "skill_heal";
    public SkillHeal() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.CLERIC)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(HEAL.skillID)
                        .setDamage(HEAL.damage)
                        .setAttackCount(HEAL.attackCount)
                        .setManaCost(HEAL.manaCost)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(7)
                        .setTextureSize(48, 51));
    }

    @Override
    public void skillEffect(Player player) {
        player.setHealth(player.getHealth() + 4);
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, HEAL.radius, HEAL.distance, true);
        scheduleDamage(player, getUndeadEntity(target));
    }

    @Override
    public void onHitEffect(Player player, LivingEntity entity) {
        if (player.level instanceof ServerLevel level) {
            level.sendParticles(new DustParticleOptions(
                            new Vector3f(0.1F, 0.95F, 0.1F), 1.0F),
                    entity.getX(), entity.getY() + entity.getBbHeight() / 2, entity.getZ(),
                    20,
                    0.3, 0.4, 0.3,
                    0.2);
        }
    }
}
