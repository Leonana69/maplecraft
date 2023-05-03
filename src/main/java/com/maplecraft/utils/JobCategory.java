package com.maplecraft.utils;

import java.util.List;

public enum JobCategory {
    NONE(0, 0, "None", List.of(0)),

    WARRIOR(1, 1, "WARRIOR", List.of(AllSkillKeyValues.POWER_STRIKE.skillID, AllSkillKeyValues.SLASH_BLAST.skillID)),
    MAGICIAN(2, 1, "MAGICIAN", List.of(AllSkillKeyValues.TELEPORT.skillID, AllSkillKeyValues.MAGIC_CLAW.skillID)),
    ARCHER(3, 1, "ARCHER", List.of(AllSkillKeyValues.ARROW_BLOW.skillID, AllSkillKeyValues.DOUBLE_SHOT.skillID)),
    ROGUE(4, 1, "ROGUE", List.of(AllSkillKeyValues.DOUBLE_STAB.skillID, AllSkillKeyValues.LUCKY_SEVEN.skillID)),

    FIGHTER(11, 2, "FIGHTER", List.of(AllSkillKeyValues.RAGE.skillID, AllSkillKeyValues.SHOUT.skillID)),
    SPEARMAN(13, 2, "SPEARMAN", List.of(AllSkillKeyValues.IRON_WILL.skillID, AllSkillKeyValues.DRAGON_FURY.skillID)),

    WIZARD_FP(21, 2, "WIZARD F/P", List.of(AllSkillKeyValues.FIRE_ARROW.skillID, AllSkillKeyValues.POISON_BRACE.skillID)),
    WIZARD_IL(22, 2, "WIZARD I/L", List.of(AllSkillKeyValues.COLD_BEAM.skillID, AllSkillKeyValues.THUNDERBOLT.skillID)),
    CLERIC(23, 2, "CLERIC", List.of(AllSkillKeyValues.HEAL.skillID, AllSkillKeyValues.HOLY_ARROW.skillID)),

    HUNTER(31, 2, "HUNTER", List.of(AllSkillKeyValues.SOUL_ARROW.skillID, AllSkillKeyValues.ARROW_BOMB.skillID)),
    CROSSBOWMAN(32, 2, "CROSSBOWMAN", List.of(AllSkillKeyValues.SOUL_ARROW.skillID, AllSkillKeyValues.IRON_ARROW.skillID)),

    ASSASSIN(41, 2, "ASSASSIN", List.of(AllSkillKeyValues.HASTE.skillID, AllSkillKeyValues.DRAIN.skillID)),
    BANDIT(42, 2, "BANDIT", List.of(AllSkillKeyValues.HASTE.skillID, AllSkillKeyValues.SAVAGE_BLOW.skillID)),

    CRUSADER(111, 3, "CRUSADER", List.of(AllSkillKeyValues.PANIC.skillID, AllSkillKeyValues.COMBO_ATTACK.skillID)),
    DRAGON_KNIGHT(131, 3, "DRAGON KNIGHT", List.of(AllSkillKeyValues.SPEAR_CRUSHER.skillID, AllSkillKeyValues.DRAGON_ROAR.skillID)),

    MAGE_FP(211, 3, "MAGE F/P", List.of(AllSkillKeyValues.EXPLOSION.skillID, AllSkillKeyValues.ELEMENT_COMPOSITION_FP.skillID)),
    MAGE_IL(221, 3, "MAGE I/L", List.of(AllSkillKeyValues.ICE_STRIKE.skillID, AllSkillKeyValues.ELEMENT_COMPOSITION_IL.skillID)),
    PRIEST(231, 3, "PRIEST", List.of(AllSkillKeyValues.SHINING_RAY.skillID, AllSkillKeyValues.HOLY_DRAGON.skillID)),

    RANGER(311, 3, "RANGER", List.of(AllSkillKeyValues.STRAFE.skillID, AllSkillKeyValues.ARROW_RAIN.skillID)),
    SNIPER(321, 3, "SNIPER", List.of(AllSkillKeyValues.STRAFE.skillID, AllSkillKeyValues.ARROW_ERUPTION.skillID)),

    HERMIT(411, 3, "HERMIT", List.of(AllSkillKeyValues.SHADOW_PARTNER.skillID, AllSkillKeyValues.AVENGER.skillID)),
    CHIEF_BANDIT(421, 3, "CHIEF BANDIT", List.of(AllSkillKeyValues.ASSAULTER.skillID, AllSkillKeyValues.MESO_EXPLOSION.skillID));

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
