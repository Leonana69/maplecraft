package com.maplecraft.item.skill;

import com.maplecraft.item.SkillItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class SkillTeleport extends SkillItem {
    public static String itemName = "skill_teleport";
    public SkillTeleport() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.MAGICIAN)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(AllSkillKeyValues.TELEPORT.skillId)
                        .setDamage(AllSkillKeyValues.TELEPORT.damage)
                        .setAttackCount(AllSkillKeyValues.TELEPORT.attackCount)
                        .setManaCost(AllSkillKeyValues.TELEPORT.manaCost)
                        .setCooldown(AllSkillKeyValues.TELEPORT.cooldown),
                new SkillEffectInstance());
    }

    @Override
    public void skillEffect(Player player) {
        if (player.level instanceof ServerLevel level) {
            level.sendParticles(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.LAPIS_BLOCK.defaultBlockState()),
                    player.getX(), player.getY() + player.getBbHeight() / 2, player.getZ(),
                    40,
                    0.2, 0.6, 0.2,
                    0.2);
        }
        Vec3 pos = player.position().add(player.getViewVector(0).scale(AllSkillKeyValues.TELEPORT.distance));
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
