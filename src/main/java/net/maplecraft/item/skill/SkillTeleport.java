package net.maplecraft.item.skill;

import net.maplecraft.network.Variables;
import net.maplecraft.utils.EquipCategory;
import net.maplecraft.utils.JobCategory;
import net.maplecraft.utils.SkillBaseData;
import net.maplecraft.utils.SkillItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class SkillTeleport extends SkillItem {
    public static String itemName = "skill_teleport";
    public static int skillID = 2001009;
    public SkillTeleport() {
        super(itemName, new SkillBaseData()
                .skillID(skillID)
                .jobReq(JobCategory.MAGICIAN)
                .weaponReq(EquipCategory.SWORD)
                .manaCost(2, 0.2));
    }

    @Override
    public void skillEffect(Player player) {
        Vec3 newPosition = player.position().add(player.getViewVector(0).scale(4));

        for (int i = 0; i < 2; i++) {
            System.out.println(newPosition);
            if (player.level.isEmptyBlock(new BlockPos(newPosition))) {
                player.setPos(newPosition);
                return;
            }
            newPosition = newPosition.add(0, 1, 0);
        }
    }
}
