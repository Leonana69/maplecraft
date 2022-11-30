package net.maplecraft.item.skill;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.utils.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;

import static net.maplecraft.utils.AllSkillKeyValues.LUCKY_SEVEN;
import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;

public class SkillLuckySeven extends SkillItem {
    public static String itemName = "skill_lucky_seven";
    public SkillLuckySeven() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.THIEF)
                        .weaponReq(EquipCategory.CLAW)
                        .skillID(LUCKY_SEVEN.skillID)
                        .damage(LUCKY_SEVEN.damage)
                        .attackCount(LUCKY_SEVEN.attackCount)
                        .manaCost(LUCKY_SEVEN.manaCost)
                        .attackInterval(4),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(4)
                        .textureSize(50, 51));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getClosestEntity(player, LUCKY_SEVEN.radius, LUCKY_SEVEN.distance);
        scheduleProjectile(player, target);
    }
}
