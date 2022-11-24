package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class SkillTeleport extends SkillItem {
    public static String itemName = "skill_teleport";
    public static int skillID = 2001009;
    public SkillTeleport() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .damage(0)
                        .jobReq(JobCategory.MAGICIAN)
                        .weaponReq(EquipCategory.WAND)
                        .manaCost(1),
                new SkillHitEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        Vec3 pos = player.position().add(player.getViewVector(0).scale(4));
        pos = new Vec3(Math.ceil(pos.x), Math.ceil(pos.y), Math.ceil(pos.z));
        for (int i = 0; i < 2; i++) {
            if (player.level.isEmptyBlock(new BlockPos(pos))) {
                player.setPos(pos);
                return;
            }
            pos = pos.add(0, 1, 0);
        }
    }
}
