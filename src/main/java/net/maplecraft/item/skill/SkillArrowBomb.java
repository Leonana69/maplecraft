package net.maplecraft.item.skill;

import net.maplecraft.entities.BombArrowEntity;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.ARROW_BOMB;

public class SkillArrowBomb extends SkillItem {
    public static String itemName = "skill_arrow_bomb";
    public SkillArrowBomb() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.HUNTER)
                        .weaponReq(EquipCategory.BOW)
                        .skillID(ARROW_BOMB.skillID)
                        .damage(ARROW_BOMB.damage)
                        .attackCount(ARROW_BOMB.attackCount)
                        .manaCost(ARROW_BOMB.manaCost),
                new SkillEffectInstance());
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, ARROW_BOMB.radius, ARROW_BOMB.distance);
        scheduleProjectile(player, target);
    }

    @Override
    public MapleProjectileEntity createArrow(Player player) {
        return new BombArrowEntity(player.level, player);
    }
}
