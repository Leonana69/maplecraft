package net.maplecraft.item.skill;

import net.maplecraft.init.CustomEffectsInit;
import net.maplecraft.utils.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillHeal extends SkillItem {
    public static String itemName = "skill_heal";
    public static int skillID = 2301002;
    public SkillHeal() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.CLERIC)
                        .weaponReq(EquipCategory.WAND)
                        .damage(80)
                        .manaCost(4)
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
            List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, 8, 0, true);
            target = getUndeadEntity(target);
            if (!target.isEmpty()) {
                scheduleDamage(player, target);
            }
        }
    }
}
