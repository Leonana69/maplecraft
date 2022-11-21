package net.maplecraft.item.skill;

import net.maplecraft.utils.EquipCategory;
import net.maplecraft.utils.JobCategory;
import net.maplecraft.utils.SkillBaseData;
import net.maplecraft.utils.SkillItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class SkillMagicClaw extends SkillItem {
    public static String itemName = "skill_magic_claw";
    public static int skillID = 2001005;
    public SkillMagicClaw() {
        super(itemName, new SkillBaseData()
                .skillID(skillID)
                .jobReq(JobCategory.MAGICIAN)
                .weaponReq(EquipCategory.WAND)
                .manaCost(4));
    }

    @Override
    public void skillEffect(Player player) {
        System.out.println("magic claw");
    }
}
