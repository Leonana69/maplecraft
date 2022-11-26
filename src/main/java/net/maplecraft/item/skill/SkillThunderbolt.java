package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillThunderbolt extends SkillItem {
    public static String itemName = "skill_thunderbolt";
    public static int skillID = 2201005;
    public SkillThunderbolt() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .damage(100)
                        .delay(10)
                        .jobReq(JobCategory.WIZARD_IL)
                        .weaponReq(EquipCategory.WAND)
                        .manaCost(4)
                        .isMagic(true),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(8)
                        .textureSize(68, 219));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, 8, 0, true);
            scheduleDamage(player, target);
        }
    }
}
