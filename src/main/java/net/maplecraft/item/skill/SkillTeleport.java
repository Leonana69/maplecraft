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
                        .setJobReq(JobCategory.MAGICIAN)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(TELEPORT.skillID)
                        .setDamage(TELEPORT.damage)
                        .setAttackCount(TELEPORT.attackCount)
                        .setManaCost(TELEPORT.manaCost),
                new SkillEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        Vec3 pos = player.position().add(player.getViewVector(0).scale(TELEPORT.distance));
        pos = new Vec3(Math.round(pos.x), Math.floor(pos.y), Math.round(pos.z));
        for (int i = 0; i < 3; i++) {
            if (player.level.isEmptyBlock(new BlockPos(pos))) {
                player.moveTo(pos);
                return;
            }
            pos = pos.add(0, 1, 0);
        }
    }
}
