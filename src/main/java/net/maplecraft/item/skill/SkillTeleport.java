package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import static net.maplecraft.utils.AllSkillKeyValues.TELEPORT;

public class SkillTeleport extends SkillItem {
    public static String itemName = "skill_teleport";
    public SkillTeleport() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.MAGICIAN)
                        .weaponReq(EquipCategory.WAND)
                        .skillID(TELEPORT.skillID)
                        .damage(TELEPORT.damage)
                        .attackCount(TELEPORT.attackCount)
                        .manaCost(TELEPORT.manaCost),
                new SkillHitEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        Vec3 pos = player.position().add(player.getViewVector(0).scale(TELEPORT.distance));
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
