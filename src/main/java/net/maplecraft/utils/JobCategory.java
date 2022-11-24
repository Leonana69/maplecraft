package net.maplecraft.utils;

import java.util.List;

public enum JobCategory {
    NONE(0, 0, "None", List.of(0)),
    WARRIOR(1, 1, "WARRIOR", List.of(1001004, 1001005)),
    MAGICIAN(2, 1, "MAGICIAN", List.of(2001009, 2001005)),
    BOWMAN(3, 1, "BOWMAN", List.of(0)),
    THIEF(4, 1, "THIEF", List.of(4001344)),
    BISHOP(21, 2, "BISHOP", List.of(0)),
    ICE_LIGHTNING(22, 2, "ICE/LIGHTNING", List.of(2201004, 2201005));

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
