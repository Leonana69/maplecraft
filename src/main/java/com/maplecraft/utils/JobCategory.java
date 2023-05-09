package com.maplecraft.utils;

import java.util.List;

public enum JobCategory {
    NONE(0, 0, "None", List.of(0)),

    WARRIOR(1, 1, "WARRIOR", List.of(AllSkillKeyValues.POWER_STRIKE.skillId, AllSkillKeyValues.SLASH_BLAST.skillId)),
    MAGICIAN(2, 1, "MAGICIAN", List.of(AllSkillKeyValues.TELEPORT.skillId, AllSkillKeyValues.MAGIC_CLAW.skillId)),
    ARCHER(3, 1, "ARCHER", List.of(AllSkillKeyValues.ARROW_BLOW.skillId, AllSkillKeyValues.DOUBLE_SHOT.skillId)),
    ROGUE(4, 1, "ROGUE", List.of(AllSkillKeyValues.DOUBLE_STAB.skillId, AllSkillKeyValues.LUCKY_SEVEN.skillId)),

    FIGHTER(11, 2, "FIGHTER", List.of(AllSkillKeyValues.RAGE.skillId, AllSkillKeyValues.SHOUT.skillId)),
    SPEARMAN(13, 2, "SPEARMAN", List.of(AllSkillKeyValues.IRON_WILL.skillId, AllSkillKeyValues.DRAGON_FURY.skillId)),

    WIZARD_FP(21, 2, "WIZARD F/P", List.of(AllSkillKeyValues.FIRE_ARROW.skillId, AllSkillKeyValues.POISON_BRACE.skillId)),
    WIZARD_IL(22, 2, "WIZARD I/L", List.of(AllSkillKeyValues.COLD_BEAM.skillId, AllSkillKeyValues.THUNDERBOLT.skillId)),
    CLERIC(23, 2, "CLERIC", List.of(AllSkillKeyValues.HEAL.skillId, AllSkillKeyValues.HOLY_ARROW.skillId)),

    HUNTER(31, 2, "HUNTER", List.of(AllSkillKeyValues.SOUL_ARROW.skillId, AllSkillKeyValues.ARROW_BOMB.skillId)),
    CROSSBOWMAN(32, 2, "CROSSBOWMAN", List.of(AllSkillKeyValues.SOUL_ARROW.skillId, AllSkillKeyValues.IRON_ARROW.skillId)),

    ASSASSIN(41, 2, "ASSASSIN", List.of(AllSkillKeyValues.HASTE.skillId, AllSkillKeyValues.DRAIN.skillId)),
    BANDIT(42, 2, "BANDIT", List.of(AllSkillKeyValues.HASTE.skillId, AllSkillKeyValues.SAVAGE_BLOW.skillId)),

    CRUSADER(111, 3, "CRUSADER", List.of(AllSkillKeyValues.PANIC.skillId, AllSkillKeyValues.COMBO_ATTACK.skillId)),
    DRAGON_KNIGHT(131, 3, "DRAGON KNIGHT", List.of(AllSkillKeyValues.SPEAR_CRUSHER.skillId, AllSkillKeyValues.DRAGON_ROAR.skillId)),

    MAGE_FP(211, 3, "MAGE F/P", List.of(AllSkillKeyValues.EXPLOSION.skillId, AllSkillKeyValues.ELEMENT_COMPOSITION_FP.skillId)),
    MAGE_IL(221, 3, "MAGE I/L", List.of(AllSkillKeyValues.ICE_STRIKE.skillId, AllSkillKeyValues.ELEMENT_COMPOSITION_IL.skillId)),
    PRIEST(231, 3, "PRIEST", List.of(AllSkillKeyValues.SHINING_RAY.skillId, AllSkillKeyValues.HOLY_DRAGON.skillId)),

    RANGER(311, 3, "RANGER", List.of(AllSkillKeyValues.STRAFE.skillId, AllSkillKeyValues.ARROW_RAIN.skillId)),
    SNIPER(321, 3, "SNIPER", List.of(AllSkillKeyValues.STRAFE.skillId, AllSkillKeyValues.ARROW_ERUPTION.skillId)),

    HERMIT(411, 3, "HERMIT", List.of(AllSkillKeyValues.SHADOW_PARTNER.skillId, AllSkillKeyValues.AVENGER.skillId)),
    CHIEF_BANDIT(421, 3, "CHIEF BANDIT", List.of(AllSkillKeyValues.ASSAULTER.skillId, AllSkillKeyValues.MESO_EXPLOSION.skillId));

    public static final List<JobCategory> VALUES = List.of(values());
    public static final int SIZE = VALUES.size();

    public final int type;
    public final int advancement;
    public final String typeName;
    public final List<Integer> skillList;

    JobCategory(int type, int advancement, String typeName, List<Integer> skillList) {
        this.type = type;
        this.advancement = advancement;
        this.typeName = typeName;
        this.skillList = skillList;
    }

    public boolean isSuccessor(JobCategory j) {
        if (advancement <= j.advancement) {
            return j.type / (int) Math.pow(10, j.advancement - advancement) == type;
        }
        return false;
    }

    public static JobCategory getJob(int type) {
        for (int i = 0; i < SIZE; i++) {
            if (VALUES.get(i).type == type)
                return VALUES.get(i);
        }
        return NONE;
    }

    public List<Integer> getSkillList(int advancement) {
        assert this.advancement >= advancement;
        int type = this.type / (int) Math.pow(10, this.advancement - advancement);
        return getJob(type).skillList;
    }
}
