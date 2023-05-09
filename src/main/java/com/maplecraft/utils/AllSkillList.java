package com.maplecraft.utils;

import com.maplecraft.init.ItemsInit;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;

public class AllSkillList {
    public static Map<Integer, ItemLike> SKILLS  = new HashMap<>() {{
        put(AllSkillKeyValues.POWER_STRIKE.skillId, ItemsInit.SKILL_POWER_STRIKE.get());
        put(AllSkillKeyValues.SLASH_BLAST.skillId, ItemsInit.SKILL_SLASH_BLAST.get());

        put(AllSkillKeyValues.RAGE.skillId, ItemsInit.SKILL_RAGE.get());
        put(AllSkillKeyValues.SHOUT.skillId, ItemsInit.SKILL_SHOUT.get());

        put(AllSkillKeyValues.COMBO_ATTACK.skillId, ItemsInit.SKILL_COMBO_ATTACK.get());
        put(AllSkillKeyValues.PANIC.skillId, ItemsInit.SKILL_PANIC.get());

        put(AllSkillKeyValues.IRON_WILL.skillId, ItemsInit.SKILL_IRON_WILL.get());
        put(AllSkillKeyValues.DRAGON_FURY.skillId, ItemsInit.SKILL_DRAGON_FURY.get());

        put(AllSkillKeyValues.SPEAR_CRUSHER.skillId, ItemsInit.SKILL_SPEAR_CRUSHER.get());
        put(AllSkillKeyValues.DRAGON_ROAR.skillId, ItemsInit.SKILL_DRAGON_ROAR.get());

        put(AllSkillKeyValues.TELEPORT.skillId, ItemsInit.SKILL_TELEPORT.get());
        put(AllSkillKeyValues.MAGIC_CLAW.skillId, ItemsInit.SKILL_MAGIC_CLAW.get());

        put(AllSkillKeyValues.FIRE_ARROW.skillId, ItemsInit.SKILL_FIRE_ARROW.get());
        put(AllSkillKeyValues.POISON_BRACE.skillId, ItemsInit.SKILL_POISON_BRACE.get());

        put(AllSkillKeyValues.EXPLOSION.skillId, ItemsInit.SKILL_EXPLOSION.get());
        put(AllSkillKeyValues.ELEMENT_COMPOSITION_FP.skillId, ItemsInit.SKILL_ELEMENT_COMPOSITION_FP.get());

        put(AllSkillKeyValues.COLD_BEAM.skillId, ItemsInit.SKILL_COLD_BEAM.get());
        put(AllSkillKeyValues.THUNDERBOLT.skillId, ItemsInit.SKILL_THUNDERBOLT.get());

        put(AllSkillKeyValues.ICE_STRIKE.skillId, ItemsInit.SKILL_ICE_STRIKE.get());
        put(AllSkillKeyValues.ELEMENT_COMPOSITION_IL.skillId, ItemsInit.SKILL_ELEMENT_COMPOSITION_IL.get());

        put(AllSkillKeyValues.HEAL.skillId, ItemsInit.SKILL_HEAL.get());
        put(AllSkillKeyValues.HOLY_ARROW.skillId, ItemsInit.SKILL_HOLY_ARROW.get());

        put(AllSkillKeyValues.SHINING_RAY.skillId, ItemsInit.SKILL_SHINING_RAY.get());
        put(AllSkillKeyValues.HOLY_DRAGON.skillId, ItemsInit.SKILL_HOLY_DRAGON.get());

        put(AllSkillKeyValues.ARROW_BLOW.skillId, ItemsInit.SKILL_ARROW_BLOW.get());
        put(AllSkillKeyValues.DOUBLE_SHOT.skillId, ItemsInit.SKILL_DOUBLE_SHOT.get());

        put(AllSkillKeyValues.SOUL_ARROW.skillId, ItemsInit.SKILL_SOUL_ARROW.get());
        put(AllSkillKeyValues.ARROW_BOMB.skillId, ItemsInit.SKILL_ARROW_BOMB.get());

        put(AllSkillKeyValues.IRON_ARROW.skillId, ItemsInit.SKILL_IRON_ARROW.get());

        put(AllSkillKeyValues.STRAFE.skillId, ItemsInit.SKILL_STRAFE.get());
        put(AllSkillKeyValues.ARROW_RAIN.skillId, ItemsInit.SKILL_ARROW_RAIN.get());

        put(AllSkillKeyValues.ARROW_ERUPTION.skillId, ItemsInit.SKILL_ARROW_ERUPTION.get());

        put(AllSkillKeyValues.LUCKY_SEVEN.skillId, ItemsInit.SKILL_LUCKY_SEVEN.get());
        put(AllSkillKeyValues.DOUBLE_STAB.skillId, ItemsInit.SKILL_DOUBLE_STAB.get());

        put(AllSkillKeyValues.HASTE.skillId, ItemsInit.SKILL_HASTE.get());
        put(AllSkillKeyValues.DRAIN.skillId, ItemsInit.SKILL_DRAIN.get());

        put(AllSkillKeyValues.SAVAGE_BLOW.skillId, ItemsInit.SKILL_SAVAGE_BLOW.get());

        put(AllSkillKeyValues.SHADOW_PARTNER.skillId, ItemsInit.SKILL_SHADOW_PARTNER.get());
        put(AllSkillKeyValues.AVENGER.skillId, ItemsInit.SKILL_AVENGER.get());

        put(AllSkillKeyValues.ASSAULTER.skillId, ItemsInit.SKILL_ASSAULTER.get());
        put(AllSkillKeyValues.MESO_EXPLOSION.skillId, ItemsInit.SKILL_MESO_EXPLOSION.get());
    }};
}
