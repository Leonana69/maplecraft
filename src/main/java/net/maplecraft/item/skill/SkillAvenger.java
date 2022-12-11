package net.maplecraft.item.skill;

import net.maplecraft.entities.AvengerEntity;
import net.maplecraft.utils.*;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

import static net.maplecraft.utils.AllSkillKeyValues.AVENGER;

public class SkillAvenger extends SkillItem {
    public static String itemName = "skill_avenger";
    public SkillAvenger() {
        super(itemName,
                new SkillBaseData()
                        .jobReq(JobCategory.HERMIT)
                        .weaponReq(EquipCategory.CLAW)
                        .skillID(AVENGER.skillID)
                        .damage(AVENGER.damage)
                        .attackCount(AVENGER.attackCount)
                        .manaCost(AVENGER.manaCost),
                new SkillEffectInstance());
        this.consumeProjectile = true;
        this.projectilePierceLevel = 3;
    }

    @Override
    public void skillEffect(Player player) {
        scheduleProjectile(player, new ArrayList<>());
    }

    @Override
    public MapleProjectileEntity createArrow(Player player) {
        return new AvengerEntity(player.level, player);
    }
}
