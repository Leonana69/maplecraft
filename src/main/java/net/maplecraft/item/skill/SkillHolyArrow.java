package net.maplecraft.item.skill;

import net.maplecraft.entities.HolyArrowEntity;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillHolyArrow extends SkillItem {
    public static String itemName = "skill_holy_arrow";
    public static int skillID = 2301005;
    public SkillHolyArrow() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.CLERIC)
                        .weaponReq(EquipCategory.WAND)
                        .damage(150)
                        .manaCost(3)
                        .isMagic(true),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(3)
                        .textureSize(136, 136));
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
        return new HolyArrowEntity(player.level, player);
    }
}
