package net.maplecraft.utils;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.item.skill.*;
import net.minecraft.world.level.ItemLike;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class AllSkillList {
    public static Map<Integer, ItemLike> SKILLS  = new HashMap<Integer, ItemLike>() {{
        put(SkillTeleport.skillID, ItemsInit.SKILL_TELEPORT.get());
        put(SkillMagicClaw.skillID, ItemsInit.SKILL_MAGIC_CLAW.get());
        put(SkillThunderBolt.skillID, ItemsInit.SKILL_THUNDER_BOLT.get());
        put(SkillColdBeam.skillID, ItemsInit.SKILL_COLD_BEAM.get());
        put(SkillPowerStrike.skillID, ItemsInit.SKILL_POWER_STRIKE.get());
        put(SkillSlashBlast.skillID, ItemsInit.SKILL_SLASH_BLAST.get());
        put(SkillLuckySeven.skillID, ItemsInit.SKILL_LUCKY_SEVEN.get());
    }};
}
