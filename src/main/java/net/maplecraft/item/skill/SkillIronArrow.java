package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

import static net.maplecraft.utils.AllSkillKeyValues.IRON_ARROW;

public class SkillIronArrow extends SkillItem {
    public static String itemName = "skill_iron_arrow";
    public SkillIronArrow() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.CROSSBOWMAN)
                        .weaponReq(EquipCategory.CROSSBOW)
                        .skillID(IRON_ARROW.skillID)
                        .damage(IRON_ARROW.damage)
                        .attackCount(IRON_ARROW.attackCount)
                        .manaCost(IRON_ARROW.manaCost),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(4)
                        .textureSize(63, 106));
        this.consumeProjectile = true;
        this.projectilePierceLevel = 3;
    }

    @Override
    public void skillEffect(Player player) {
        scheduleProjectile(player, new ArrayList<>());
    }
}
