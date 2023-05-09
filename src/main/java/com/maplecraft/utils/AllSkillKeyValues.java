package com.maplecraft.utils;

public enum AllSkillKeyValues {
    POWER_STRIKE(1001004, 160, 1, 2, 3, 2, 0.5F),
    SLASH_BLAST(1001005, 100, 1, 2, 3, 2, 2.0F),

    RAGE(1101006, 0, 0, 3, 0, 0, 1.0F),
    SHOUT(1111008, 140, 1, 4, 9, 0, 5.0F),

    COMBO_ATTACK(1111002, 0, 0, 4, 0, 0, 1.0F),
    PANIC(1111003, 160, 1, 3, 3, 2, 2.5F),

    IRON_WILL(1301006, 0, 0, 3, 0, 0, 1.0F),
    DRAGON_FURY(1311003, 120, 1, 4, 3, 4, 2.5F),

    SPEAR_CRUSHER(1311001, 110, 3, 4, 3, 4, 1.0F),
    DRAGON_ROAR(1311006, 160, 1, 6, 12, 0, 5.0F),

    TELEPORT(2001009, 0, 0, 1, 0, 4, 1.0F),
    MAGIC_CLAW(2001005, 100, 2, 3, 3, 9, 0.5F),

    FIRE_ARROW(2101004, 180, 1, 2, 4, 10, 1.0F),
    POISON_BRACE(2101005, 100, 1, 2, 4, 10, 2.5F),

    EXPLOSION(2111002, 180, 1, 4, 9, 0, 5.0F),
    ELEMENT_COMPOSITION_FP(2111006, 240, 1, 5, 4, 10, 1.0F),

    COLD_BEAM(2201004, 180, 1, 2, 3, 9, 1.0F),
    THUNDERBOLT(2201005, 120, 1, 3, 9, 0, 2.5F),

    ICE_STRIKE(2211002, 160, 1, 4, 9, 0, 5.0F),
    ELEMENT_COMPOSITION_IL(2211006, 240, 1, 5, 4, 10, 1.0F),

    HEAL(2301002, 100, 1, 3, 9, 0, 1.5F),
    HOLY_ARROW(2301005, 160, 1, 2, 4, 10, 1.0F),

    SHINING_RAY(2311004, 180, 1, 4, 10, 0, 5.0F),
    HOLY_DRAGON(2311006, 140, 1, 4, 0, 0, 1.0F),

    ARROW_BLOW(3001004, 160, 1, 1, 4, 12, 0.5F),
    DOUBLE_SHOT(3001005, 100, 2, 2, 4, 12, 0.5F),

    SOUL_ARROW(3101004, 0, 0, 3, 0, 0, 1.0F),
    ARROW_BOMB(3101005, 120, 1, 3, 4, 12, 2.5F),

    IRON_ARROW(3201005, 180, 1, 3, 4, 12, 1.5F),

    STRAFE(3111006, 100, 4, 4, 4, 12, 1.5F),
    ARROW_RAIN(3111004, 180, 1, 4, 9, 0, 4.0F),

    ARROW_ERUPTION(3211004, 180, 1, 4, 9, 0, 4.0F),

    LUCKY_SEVEN(4001344, 100, 2, 2, 4, 12, 0.5F),
    DOUBLE_STAB(4001334, 100, 2, 2, 3, 2, 0.5F),

    HASTE(4101004, 0, 0, 3, 0, 0, 1.0F),
    DRAIN(4101005, 140, 1, 3, 3, 12, 1.5F),

    SAVAGE_BLOW(4201005, 60, 6, 3, 3, 2, 1.0F),

    SHADOW_PARTNER(4111002, 60, 1, 4, 0, 0, 1.0F),
    AVENGER(4111005, 180, 1, 6, 4, 16, 2.0F),

    ASSAULTER(4211002, 220, 1, 4, 4, 3, 1.0F),
    MESO_EXPLOSION(4211006, 100, 1, 6, 9, 4, 2.0F);

    public final int skillId;
    public final int damage;
    public final int attackCount;
    public final int manaCost;
    public final int radius;
    public final int distance;
    public final float cooldown;
    AllSkillKeyValues(int skillId, int damage, int attackCount, int manaCost, int radius, int distance, float cooldown) {
        this.skillId = skillId;
        this.damage = damage;
        this.attackCount = attackCount;
        this.manaCost = manaCost;
        this.radius = radius;
        this.distance = distance;
        this.cooldown = cooldown;
    }
}
