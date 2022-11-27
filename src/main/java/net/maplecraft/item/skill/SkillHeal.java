package net.maplecraft.item.skill;

import net.maplecraft.init.CustomEffectsInit;
import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.HEAL;

public class SkillHeal extends SkillItem {
    public static String itemName = "skill_heal";
    public SkillHeal() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.CLERIC)
                        .weaponReq(EquipCategory.WAND)
                        .skillID(HEAL.skillID)
                        .damage(HEAL.damage)
                        .attackCount(HEAL.attackCount)
                        .manaCost(HEAL.damage)
                        .isMagic(true),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(7)
                        .textureSize(48, 51));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            player.setHealth(player.getHealth() + 4);
            List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, HEAL.radius, HEAL.distance, true);
            target = getUndeadEntity(target);
            if (!target.isEmpty()) {
                scheduleDamage(player, target);
            }
        }
    }
}
