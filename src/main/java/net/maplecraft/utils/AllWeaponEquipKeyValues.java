package net.maplecraft.utils;

public enum AllWeaponEquipKeyValues {
    BOW_WAR_BOW_KV(0, 3, 2),
    BOW_HUNTERS_BOW_KV(5, 5, 2),
    BOW_RYDEN_KV(10, 7, 2),
    BOW_MAPLE_BOW_KV(15, 9, 2),

    CROSSBOW_CROSSBOW_KV(0, 4, 2),

    CLAW_GARNIER_KV(0, 2, 2),
    CLAW_BRONZE_IGOR_KV(5, 4, 2),
    CLAW_BLACK_SCARAB_KV(10, 6, 2),
    CLAW_MAPLE_KANDAYO_KV(15, 8, 2),

    DAGGER_RAZOR_KV(0, 3, 2),
    DAGGER_STINGER_KV(5, 5, 2),
    DAGGER_KOREAN_FAN_KV(10, 7, 2),
    DAGGER_MAPLE_WAGNER_KV(15, 9, 2),

    SPEAR_SPEAR_KV(0, 4, 4),
    SPEAR_FORK_ON_STICK_KV(5, 6, 4),
    SPEAR_FISH_SPEAR_KV(10, 8, 4),
    SPEAR_MAPLE_IMPALER_KV(15, 10, 4),

    SWORD_SWORD_KV(0, 3, 2),
    SWORD_SABRE_KV(5, 5, 2),
    SWORD_STONETOOTH_SWORD_KV(10, 7, 2),
    SWORD_MAPLE_SWORD_KV(15, 9, 2),

    WAND_HARDWOOD_WAND_KV(0, 3, 2),
    WAND_WIZARD_STAFF_KV(5, 5, 2),
    WAND_CROMI_KV(10, 7, 2),
    WAND_MAPLE_LAMA_STAFF_KV(15, 9, 2);

    public final int levelReq;
    public final int attack;
    public final int attackSpeed;
    AllWeaponEquipKeyValues(int levelReq, int attack, int attackSpeed) {
        this.levelReq = levelReq;
        this.attack = attack;
        this.attackSpeed = attackSpeed;
    }
}
