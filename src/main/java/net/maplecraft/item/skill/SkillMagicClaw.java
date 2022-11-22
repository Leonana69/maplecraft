package net.maplecraft.item.skill;

import net.maplecraft.utils.EquipCategory;
import net.maplecraft.utils.JobCategory;
import net.maplecraft.utils.SkillBaseData;
import net.maplecraft.utils.SkillItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class SkillMagicClaw extends SkillItem {
    public static String itemName = "skill_magic_claw";
    public static int skillID = 2001005;
    public SkillMagicClaw() {
        super(itemName, new SkillBaseData()
                .skillID(skillID)
                .jobReq(JobCategory.MAGICIAN)
                .weaponReq(EquipCategory.WAND)
                .damage(150)
                .attackCount(2)
                .manaCost(4));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, 3, 7);
            if (!target.isEmpty()) {
                scheduleDamage(player, target);
            }
        }
    }
}
