package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.HEAL;

public class SkillHeal extends SkillItem {
    public static String itemName = "skill_heal";
    public SkillHeal() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.CLERIC)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(HEAL.skillID)
                        .setDamage(HEAL.damage)
                        .setAttackCount(HEAL.attackCount)
                        .setManaCost(HEAL.manaCost)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(7)
                        .setTextureSize(48, 51));
    }

    @Override
    public void skillEffect(Player player) {
        player.setHealth(player.getHealth() + 4);
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, HEAL.radius, HEAL.distance, true);
        scheduleDamage(player, getUndeadEntity(target));
    }
}
