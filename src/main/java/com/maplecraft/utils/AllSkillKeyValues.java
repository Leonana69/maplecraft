package com.maplecraft.utils;

public enum AllSkillKeyValues {
    POWER_STRIKE(1001004, 160, 1, 2, 3, 2),
    SLASH_BLAST(1001005, 100, 1, 2, 3, 2),

    RAGE(1101006, 0, 0, 3, 0, 0),
    SHOUT(1111008, 140, 1, 4, 9, 0),

    COMBO_ATTACK(1111002, 0, 0, 4, 0, 0),
    PANIC(1111003, 160, 1, 3, 3, 2),

    IRON_WILL(1301006, 0, 0, 3, 0, 0),
    DRAGON_FURY(1311003, 120, 1, 4, 3, 4),

    SPEAR_CRUSHER(1311001, 110, 3, 4, 3, 4),
    DRAGON_ROAR(1311006, 160, 1, 6, 12, 0),

    TELEPORT(2001009, 0, 0, 1, 0, 4),
    MAGIC_CLAW(2001005, 100, 2, 3, 3, 9),

    FIRE_ARROW(2101004, 180, 1, 2, 4, 10),
    POISON_BRACE(2101005, 100, 1, 2, 4, 10),

    EXPLOSION(2111002, 180, 1, 4, 9, 0),
    ELEMENT_COMPOSITION_FP(2111006, 240, 1, 5, 4, 10),

    COLD_BEAM(2201004, 180, 1, 2, 3, 9),
    THUNDERBOLT(2201005, 120, 1, 3, 9, 0),

    ICE_STRIKE(2211002, 160, 1, 4, 9, 0),
    ELEMENT_COMPOSITION_IL(2211006, 240, 1, 5, 4, 10),

    HEAL(2301002, 100, 1, 3, 9, 0),
    HOLY_ARROW(2301005, 160, 1, 2, 4, 10),

    SHINING_RAY(2311004, 180, 1, 4, 10, 0),
    HOLY_DRAGON(2311006, 140, 1, 4, 0, 0),

    ARROW_BLOW(3001004, 160, 1, 1, 4, 12),
    DOUBLE_SHOT(3001005, 100, 2, 2, 4, 12),

    SOUL_ARROW(3101004, 0, 0, 3, 0, 0),
    ARROW_BOMB(3101005, 120, 1, 3, 4, 12),

    IRON_ARROW(3201005, 180, 1, 3, 4, 12),

    STRAFE(3111006, 100, 4, 4, 4, 12),
    ARROW_RAIN(3111004, 180, 1, 4, 9, 0),

    ARROW_ERUPTION(3211004, 180, 1, 4, 9, 0),

    LUCKY_SEVEN(4001344, 100, 2, 2, 4, 12),
    DOUBLE_STAB(4001334, 100, 2, 2, 3, 2),

    HASTE(4101004, 0, 0, 3, 0, 0),
    DRAIN(4101005, 140, 1, 3, 3, 12),

    SAVAGE_BLOW(4201005, 60, 6, 3, 3, 2),

    SHADOW_PARTNER(4111002, 60, 1, 4, 0, 0),
    AVENGER(4111005, 180, 1, 6, 4, 16),

    ASSAULTER(4211002, 220, 1, 4, 4, 3),
    MESO_EXPLOSION(4211006, 100, 1, 6, 9, 4);

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
