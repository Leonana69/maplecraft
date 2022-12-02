package net.maplecraft.utils;

import java.util.List;

import static net.maplecraft.utils.AllSkillKeyValues.*;

public enum JobCategory {
    NONE(0, 0, "None", List.of(0)),
    WARRIOR(1, 1, "WARRIOR", List.of(POWER_STRIKE.skillID, SLASH_BLAST.skillID)),
    MAGICIAN(2, 1, "MAGICIAN", List.of(TELEPORT.skillID, MAGIC_CLAW.skillID)),
    ARCHER(3, 1, "ARCHER", List.of(ARROW_BLOW.skillID, DOUBLE_SHOT.skillID)),
    ROGUE(4, 1, "ROGUE", List.of(DOUBLE_STAB.skillID, LUCKY_SEVEN.skillID)),
    
    SPEARMAN(13, 2, "SPEARMAN", List.of(IRON_WILL.skillID, DRAGON_FURY.skillID)),

    WIZARD_FP(21, 2, "WIZARD F/P", List.of(FIRE_ARROW.skillID, POISON_BRACE.skillID)),
    WIZARD_IL(22, 2, "WIZARD I/L", List.of(COLD_BEAM.skillID, THUNDERBOLT.skillID)),
    CLERIC(23, 2, "CLERIC", List.of(HEAL.skillID, HOLY_ARROW.skillID)),

    HUNTER(31, 2, "HUNTER", List.of(SOUL_ARROW.skillID, ARROW_BOMB.skillID)),
    CROSSBOWMAN(32, 2, "CROSSBOWMAN", List.of(SOUL_ARROW.skillID, IRON_ARROW.skillID)),

    ASSASSIN(41, 2, "ASSASSIN", List.of(HASTE.skillID, DRAIN.skillID)),
    BANDIT(42, 2, "BANDIT", List.of(HASTE.skillID, SAVAGE_BLOW.skillID)),

    RANGER(311, 3, "RANGER", List.of(STRAFE.skillID, ARROW_RAIN.skillID));

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
