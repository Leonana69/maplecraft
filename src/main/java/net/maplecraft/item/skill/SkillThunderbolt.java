package net.maplecraft.item.skill;

import net.maplecraft.item.SkillItem;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.THUNDERBOLT;

public class SkillThunderbolt extends SkillItem {
    public static String itemName = "skill_thunderbolt";
    public SkillThunderbolt() {
        super(itemName,
                new SkillBaseData()
                        .setJobReq(JobCategory.WIZARD_IL)
                        .setWeaponReq(EquipCategory.WAND)
                        .setSkillID(THUNDERBOLT.skillID)
                        .setDamage(THUNDERBOLT.damage)
                        .setAttackCount(THUNDERBOLT.attackCount)
                        .setManaCost(THUNDERBOLT.manaCost)
                        .setDelay(10)
                        .setIsMagic(true),
                new SkillEffectInstance()
                        .setSkillName(itemName)
                        .setAnimeCount(8)
                        .setTextureSize(68, 219));
    }

    @Override
    public void skillEffect(Player player) {
        List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, THUNDERBOLT.radius, THUNDERBOLT.distance, true);
        scheduleDamage(player, target);
    }
}
