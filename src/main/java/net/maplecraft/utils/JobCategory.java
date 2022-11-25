package net.maplecraft.utils;

import net.maplecraft.item.skill.*;

import java.util.List;

public enum JobCategory {
    NONE(0, 0, "None", List.of(0)),
    WARRIOR(1, 1, "WARRIOR", List.of(SkillPowerStrike.skillID, SkillSlashBlast.skillID)),
    MAGICIAN(2, 1, "MAGICIAN", List.of(SkillTeleport.skillID, SkillMagicClaw.skillID)),
    BOWMAN(3, 1, "BOWMAN", List.of(SkillArrowBlow.skillID, SkillDoubleShot.skillID)),
    THIEF(4, 1, "THIEF", List.of(SkillDoubleStab.skillID, SkillLuckySeven.skillID)),
    BISHOP(21, 2, "BISHOP", List.of(0)),
    ICE_LIGHTNING(22, 2, "ICE/LIGHTNING", List.of(SkillColdBeam.skillID, SkillThunderbolt.skillID)),
    HUNTER(31, 2, "HUNTER", List.of(SkillArrowBomb.skillID));

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
