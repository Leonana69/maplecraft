package net.maplecraft.utils;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.item.skill.*;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;

public class AllSkillList {
    public static Map<Integer, ItemLike> SKILLS  = new HashMap<Integer, ItemLike>() {{
        put(SkillPowerStrike.skillID, ItemsInit.SKILL_POWER_STRIKE.get());
        put(SkillSlashBlast.skillID, ItemsInit.SKILL_SLASH_BLAST.get());

        put(SkillDragonFury.skillID, ItemsInit.SKILL_DRAGON_FURY.get());

        put(SkillTeleport.skillID, ItemsInit.SKILL_TELEPORT.get());
        put(SkillMagicClaw.skillID, ItemsInit.SKILL_MAGIC_CLAW.get());

        put(SkillFireArrow.skillID, ItemsInit.SKILL_FIRE_ARROW.get());

        put(SkillColdBeam.skillID, ItemsInit.SKILL_COLD_BEAM.get());
        put(SkillThunderbolt.skillID, ItemsInit.SKILL_THUNDERBOLT.get());

        put(SkillHeal.skillID, ItemsInit.SKILL_HEAL.get());
        put(SkillHolyArrow.skillID, ItemsInit.SKILL_HOLY_ARROW.get());

        put(SkillArrowBlow.skillID, ItemsInit.SKILL_ARROW_BLOW.get());
        put(SkillDoubleShot.skillID, ItemsInit.SKILL_DOUBLE_SHOT.get());

        put(SkillArrowBomb.skillID, ItemsInit.SKILL_ARROW_BOMB.get());

        put(SkillStrafe.skillID, ItemsInit.SKILL_STRAFE.get());
        put(SkillArrowRain.skillID, ItemsInit.SKILL_ARROW_RAIN.get());

        put(SkillLuckySeven.skillID, ItemsInit.SKILL_LUCKY_SEVEN.get());
        put(SkillDoubleStab.skillID, ItemsInit.SKILL_DOUBLE_STAB.get());

        put(SkillHaste.skillID, ItemsInit.SKILL_HASTE.get());
        put(SkillDrain.skillID, ItemsInit.SKILL_DRAIN.get());

        put(SkillSavageBlow.skillID, ItemsInit.SKILL_SAVAGE_BLOW.get());
    }};
}
