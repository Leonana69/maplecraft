package net.maplecraft.item.skill;

import net.maplecraft.entities.FireArrowEntity;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillFireArrow extends SkillItem {
    public static String itemName = "skill_fire_arrow";
    public static int skillID = 2101004;
    public SkillFireArrow() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.WIZARD_FP)
                        .weaponReq(EquipCategory.WAND)
                        .damage(140)
                        .manaCost(3)
                        .isMagic(true),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(5)
                        .textureSize(56, 77));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, 4, 15);
            scheduleProjectile(player, target);
        }
    }

    @Override
    public MapleProjectileEntity createArrow(Player player) {
        return new FireArrowEntity(player.level, player);
    }
}
