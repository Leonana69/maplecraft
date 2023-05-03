package com.maplecraft.utils;

import com.maplecraft.init.ItemsInit;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;

public class AllSkillList {
    public static Map<Integer, ItemLike> SKILLS  = new HashMap<>() {{
        put(AllSkillKeyValues.POWER_STRIKE.skillID, ItemsInit.SKILL_POWER_STRIKE.get());
        put(AllSkillKeyValues.SLASH_BLAST.skillID, ItemsInit.SKILL_SLASH_BLAST.get());

        put(AllSkillKeyValues.RAGE.skillID, ItemsInit.SKILL_RAGE.get());
        put(AllSkillKeyValues.SHOUT.skillID, ItemsInit.SKILL_SHOUT.get());

        put(AllSkillKeyValues.COMBO_ATTACK.skillID, ItemsInit.SKILL_COMBO_ATTACK.get());
        put(AllSkillKeyValues.PANIC.skillID, ItemsInit.SKILL_PANIC.get());

        put(AllSkillKeyValues.IRON_WILL.skillID, ItemsInit.SKILL_IRON_WILL.get());
        put(AllSkillKeyValues.DRAGON_FURY.skillID, ItemsInit.SKILL_DRAGON_FURY.get());

        put(AllSkillKeyValues.SPEAR_CRUSHER.skillID, ItemsInit.SKILL_SPEAR_CRUSHER.get());
        put(AllSkillKeyValues.DRAGON_ROAR.skillID, ItemsInit.SKILL_DRAGON_ROAR.get());

        put(AllSkillKeyValues.TELEPORT.skillID, ItemsInit.SKILL_TELEPORT.get());
        put(AllSkillKeyValues.MAGIC_CLAW.skillID, ItemsInit.SKILL_MAGIC_CLAW.get());

        put(AllSkillKeyValues.FIRE_ARROW.skillID, ItemsInit.SKILL_FIRE_ARROW.get());
        put(AllSkillKeyValues.POISON_BRACE.skillID, ItemsInit.SKILL_POISON_BRACE.get());

        put(AllSkillKeyValues.EXPLOSION.skillID, ItemsInit.SKILL_EXPLOSION.get());
        put(AllSkillKeyValues.ELEMENT_COMPOSITION_FP.skillID, ItemsInit.SKILL_ELEMENT_COMPOSITION_FP.get());

        put(AllSkillKeyValues.COLD_BEAM.skillID, ItemsInit.SKILL_COLD_BEAM.get());
        put(AllSkillKeyValues.THUNDERBOLT.skillID, ItemsInit.SKILL_THUNDERBOLT.get());

        put(AllSkillKeyValues.ICE_STRIKE.skillID, ItemsInit.SKILL_ICE_STRIKE.get());
        put(AllSkillKeyValues.ELEMENT_COMPOSITION_IL.skillID, ItemsInit.SKILL_ELEMENT_COMPOSITION_IL.get());

        put(AllSkillKeyValues.HEAL.skillID, ItemsInit.SKILL_HEAL.get());
        put(AllSkillKeyValues.HOLY_ARROW.skillID, ItemsInit.SKILL_HOLY_ARROW.get());

        put(AllSkillKeyValues.SHINING_RAY.skillID, ItemsInit.SKILL_SHINING_RAY.get());
        put(AllSkillKeyValues.HOLY_DRAGON.skillID, ItemsInit.SKILL_HOLY_DRAGON.get());

        put(AllSkillKeyValues.ARROW_BLOW.skillID, ItemsInit.SKILL_ARROW_BLOW.get());
        put(AllSkillKeyValues.DOUBLE_SHOT.skillID, ItemsInit.SKILL_DOUBLE_SHOT.get());

        put(AllSkillKeyValues.SOUL_ARROW.skillID, ItemsInit.SKILL_SOUL_ARROW.get());
        put(AllSkillKeyValues.ARROW_BOMB.skillID, ItemsInit.SKILL_ARROW_BOMB.get());

        put(AllSkillKeyValues.IRON_ARROW.skillID, ItemsInit.SKILL_IRON_ARROW.get());

        put(AllSkillKeyValues.STRAFE.skillID, ItemsInit.SKILL_STRAFE.get());
        put(AllSkillKeyValues.ARROW_RAIN.skillID, ItemsInit.SKILL_ARROW_RAIN.get());

        put(AllSkillKeyValues.ARROW_ERUPTION.skillID, ItemsInit.SKILL_ARROW_ERUPTION.get());

        put(AllSkillKeyValues.LUCKY_SEVEN.skillID, ItemsInit.SKILL_LUCKY_SEVEN.get());
        put(AllSkillKeyValues.DOUBLE_STAB.skillID, ItemsInit.SKILL_DOUBLE_STAB.get());

        put(AllSkillKeyValues.HASTE.skillID, ItemsInit.SKILL_HASTE.get());
        put(AllSkillKeyValues.DRAIN.skillID, ItemsInit.SKILL_DRAIN.get());

        put(AllSkillKeyValues.SAVAGE_BLOW.skillID, ItemsInit.SKILL_SAVAGE_BLOW.get());

        put(AllSkillKeyValues.SHADOW_PARTNER.skillID, ItemsInit.SKILL_SHADOW_PARTNER.get());
        put(AllSkillKeyValues.AVENGER.skillID, ItemsInit.SKILL_AVENGER.get());

        put(AllSkillKeyValues.ASSAULTER.skillID, ItemsInit.SKILL_ASSAULTER.get());
        put(AllSkillKeyValues.MESO_EXPLOSION.skillID, ItemsInit.SKILL_MESO_EXPLOSION.get());
    }};
}
