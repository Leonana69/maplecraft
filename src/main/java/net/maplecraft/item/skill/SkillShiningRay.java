package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.HEAL;
import static net.maplecraft.utils.AllSkillKeyValues.SHINING_RAY;

public class SkillShiningRay extends SkillItem {
    public static String itemName = "skill_shining_ray";
    public SkillShiningRay() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.PRIEST)
                        .weaponReq(EquipCategory.WAND)
                        .skillID(SHINING_RAY.skillID)
                        .damage(SHINING_RAY.damage)
                        .attackCount(SHINING_RAY.attackCount)
                        .manaCost(SHINING_RAY.manaCost)
                        .delay(10)
                        .isMagic(true),
                new SkillEffectInstance()
                        .skillName(itemName)
                        .animeCount(2)
                        .textureSize(137, 143));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, SHINING_RAY.radius, SHINING_RAY.distance, true);
        scheduleDamage(player, getUndeadEntity(target), 1.2F);
        scheduleDamage(player, getLivingEntity(target));
    }
}
