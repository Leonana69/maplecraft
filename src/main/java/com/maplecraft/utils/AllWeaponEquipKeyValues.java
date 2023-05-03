package com.maplecraft.utils;

public enum AllWeaponEquipKeyValues {
    BOW_WAR_BOW_KV(0, 4, 2),
    BOW_HUNTERS_BOW_KV(5, 7, 2),
    BOW_RYDEN_KV(10, 10, 2),
    BOW_MAPLE_BOW_KV(15, 13, 2),

    CROSSBOW_CROSSBOW_KV(0, 4, 2),
    CROSSBOW_BALANCHE_KV(5, 7, 2),
    CROSSBOW_GOLDEN_RAVEN_KV(10, 10, 2),
    CROSSBOW_MAPLE_CROSSBOW_KV(15, 13, 2),

    CLAW_GARNIER_KV(0, 3, 2),
    CLAW_BRONZE_IGOR_KV(5, 6, 2),
    CLAW_BLACK_SCARAB_KV(10, 9, 2),
    CLAW_MAPLE_KANDAYO_KV(15, 12, 2),

    DAGGER_RAZOR_KV(0, 4, 2),
    DAGGER_STINGER_KV(5, 7, 2),
    DAGGER_KOREAN_FAN_KV(10, 10, 2),
    DAGGER_MAPLE_WAGNER_KV(15, 13, 2),

    SPEAR_SPEAR_KV(0, 6, 4),
    SPEAR_FORK_ON_STICK_KV(5, 9, 4),
    SPEAR_FISH_SPEAR_KV(10, 12, 4),
    SPEAR_MAPLE_IMPALER_KV(15, 15, 4),

    SWORD_SWORD_KV(0, 4, 2),
    SWORD_SABRE_KV(5, 7, 2),
    SWORD_STONETOOTH_SWORD_KV(10, 10, 2),
    SWORD_MAPLE_SWORD_KV(15, 13, 2),

    WAND_HARDWOOD_WAND_KV(0, 4, 2),
    WAND_WIZARD_STAFF_KV(5, 7, 2),
    WAND_CROMI_KV(10, 10, 2),
    WAND_MAPLE_LAMA_STAFF_KV(15, 13, 2);

    public final int levelReq;
    public final int attack;
    public final int attackSpeed;
    AllWeaponEquipKeyValues(int levelReq, int attack, int attackSpeed) {
        this.levelReq = levelReq;
        this.attack = attack;
        this.attackSpeed = attackSpeed;
    }
}
