package net.maplecraft.utils;

public enum AllSkillKeyValues {
    POWER_STRIKE(1001004, 180, 1, 2, 3, 2),
    SLASH_BLAST(1001005, 100, 1, 3, 3, 2),

    DRAGON_FURY(1311003, 120, 1, 4, 3, 4),

    TELEPORT(2001009, 0, 0, 1, 0, 4),
    MAGIC_CLAW(2001005, 90, 2, 4, 3, 9),

    FIRE_ARROW(2101004, 140, 1, 3, 4, 10),
    POISON_BRACE(2101005, 100, 1, 3, 4, 10),

    COLD_BEAM(2201004, 150, 1, 3, 3, 9),
    THUNDERBOLT(2201005, 100, 1, 4, 9, 0),

    HEAL(2301002, 80, 1, 4, 9, 0),
    HOLY_ARROW(2301005, 140, 1, 3, 4, 10),

    ARROW_BLOW(3001004, 180, 1, 4, 4, 12),
    DOUBLE_SHOT(3001005, 100, 2, 3, 4, 12),

    ARROW_BOMB(3101005, 120, 1, 6, 4, 12),

    IRON_ARROW(3201005, 160, 1, 6, 4, 12),

    STRAFE(3111006, 100, 4, 6, 4, 12),
    ARROW_RAIN(3111004, 150, 1, 6, 9, 0),

    LUCKY_SEVEN(4001344, 100, 2, 3, 4, 12),
    DOUBLE_STAB(4001334, 100, 2, 3, 3, 2),

    HASTE(4101004, 0, 0, 3, 0, 0),
    DRAIN(4101005, 150, 1, 6, 4, 12),

    SAVAGE_BLOW(4201005, 60, 6, 6, 3, 2);

    public final int skillID;
    public final int damage;
    public final int attackCount;
    public final int manaCost;
    public final int radius;
    public final int distance;
    AllSkillKeyValues(int skillID, int damage, int attackCount, int manaCost, int radius, int distance) {
        this.skillID = skillID;
        this.damage = damage;
        this.attackCount = attackCount;
        this.manaCost = manaCost;
        this.radius = radius;
        this.distance = distance;
    }
}
