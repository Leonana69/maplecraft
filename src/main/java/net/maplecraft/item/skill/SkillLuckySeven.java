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

import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;

public class SkillLuckySeven extends SkillItem {
    public static String itemName = "skill_lucky_seven";
    public static int skillID = 4001344;
    public static int cnt = 0;
    public SkillLuckySeven() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.THIEF)
                        .weaponReq(EquipCategory.CLAW)
                        .damage(100)
                        .attackCount(2)
                        .attackInterval(4)
                        .manaCost(2),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(4)
                        .textureSize(50, 51));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, 3, 10);
            scheduleProjectile(player, target);
        }
    }
}
