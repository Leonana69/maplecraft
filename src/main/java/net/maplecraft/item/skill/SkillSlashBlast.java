package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class SkillSlashBlast extends SkillItem {
    public static String itemName = "skill_slash_blast";
    public static int skillID = 1001005;
    public SkillSlashBlast() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.WARRIOR)
                        .weaponReq(EquipCategory.SWORD)
                        .manaCost(4)
                        .healthCost(1),
                new SkillHitEffect());
    }

    @Override
    public void skillEffect(Player player) {
//        Vec3 newPosition = player.position().add(player.getViewVector(0).scale(4));
//
//        for (int i = 0; i < 2; i++) {
//            System.out.println(newPosition);
//            if (player.level.isEmptyBlock(new BlockPos(newPosition))) {
//                player.setPos(newPosition);
//                return;
//            }
//            newPosition = newPosition.add(0, 1, 0);
//        }
    }
}
