package net.maplecraft.utils;

import net.maplecraft.init.ItemsInit;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;

import static net.maplecraft.utils.AllSkillKeyValues.*;

public class AllSkillList {
    public static Map<Integer, ItemLike> SKILLS  = new HashMap<Integer, ItemLike>() {{
        put(POWER_STRIKE.skillID, ItemsInit.SKILL_POWER_STRIKE.get());
        put(SLASH_BLAST.skillID, ItemsInit.SKILL_SLASH_BLAST.get());

        put(RAGE.skillID, ItemsInit.SKILL_RAGE.get());
        put(SHOUT.skillID, ItemsInit.SKILL_SHOUT.get());

        put(COMBO_ATTACK.skillID, ItemsInit.SKILL_COMBO_ATTACK.get());
        put(PANIC.skillID, ItemsInit.SKILL_PANIC.get());

        put(IRON_WILL.skillID, ItemsInit.SKILL_IRON_WILL.get());
        put(DRAGON_FURY.skillID, ItemsInit.SKILL_DRAGON_FURY.get());

        put(SPEAR_CRUSHER.skillID, ItemsInit.SKILL_SPEAR_CRUSHER.get());
        put(DRAGON_ROAR.skillID, ItemsInit.SKILL_DRAGON_ROAR.get());

        put(TELEPORT.skillID, ItemsInit.SKILL_TELEPORT.get());
        put(MAGIC_CLAW.skillID, ItemsInit.SKILL_MAGIC_CLAW.get());

        put(FIRE_ARROW.skillID, ItemsInit.SKILL_FIRE_ARROW.get());
        put(POISON_BRACE.skillID, ItemsInit.SKILL_POISON_BRACE.get());

        put(COLD_BEAM.skillID, ItemsInit.SKILL_COLD_BEAM.get());
        put(THUNDERBOLT.skillID, ItemsInit.SKILL_THUNDERBOLT.get());

        put(HEAL.skillID, ItemsInit.SKILL_HEAL.get());
        put(HOLY_ARROW.skillID, ItemsInit.SKILL_HOLY_ARROW.get());

        put(ARROW_BLOW.skillID, ItemsInit.SKILL_ARROW_BLOW.get());
        put(DOUBLE_SHOT.skillID, ItemsInit.SKILL_DOUBLE_SHOT.get());

        put(SOUL_ARROW.skillID, ItemsInit.SKILL_SOUL_ARROW.get());
        put(ARROW_BOMB.skillID, ItemsInit.SKILL_ARROW_BOMB.get());

        put(IRON_ARROW.skillID, ItemsInit.SKILL_IRON_ARROW.get());

        put(STRAFE.skillID, ItemsInit.SKILL_STRAFE.get());
        put(ARROW_RAIN.skillID, ItemsInit.SKILL_ARROW_RAIN.get());

        put(ARROW_ERUPTION.skillID, ItemsInit.SKILL_ARROW_ERUPTION.get());

        put(LUCKY_SEVEN.skillID, ItemsInit.SKILL_LUCKY_SEVEN.get());
        put(DOUBLE_STAB.skillID, ItemsInit.SKILL_DOUBLE_STAB.get());

        put(HASTE.skillID, ItemsInit.SKILL_HASTE.get());
        put(DRAIN.skillID, ItemsInit.SKILL_DRAIN.get());

        put(SAVAGE_BLOW.skillID, ItemsInit.SKILL_SAVAGE_BLOW.get());

        put(SHADOW_PARTNER.skillID, ItemsInit.SKILL_SHADOW_PARTNER.get());
        put(AVENGER.skillID, ItemsInit.SKILL_AVENGER.get());
    }};
}
