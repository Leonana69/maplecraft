package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectsInit {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MapleCraftMod.MODID);
    public static final RegistryObject<MobEffect> EQUIP_SPEED_BOOST = REGISTRY.register("equip_speed_boost", EquipSpeedPercentBoostMobEffect::new);
    public static final RegistryObject<MobEffect> EQUIP_HEALTH_BOOST = REGISTRY.register("equip_health_boost", EquipHealthBoostMobEffect::new);
    public static final RegistryObject<MobEffect> EQUIP_ATTACK_BOOST = REGISTRY.register("equip_attack_boost", EquipAttackBoostMobEffect::new);
    public static final RegistryObject<MobEffect> EQUIP_ATTACK_PERCENT_BOOST = REGISTRY.register("equip_attack_percent_boost", EquipAttackPercentBoostMobEffect::new);
    public static final RegistryObject<MobEffect> EQUIP_ATTACK_SPEED_BOOST = REGISTRY.register("equip_attack_speed_boost", EquipAttackSpeedBoostMobEffect::new);
    public static final RegistryObject<MobEffect> DEFENSE_PERCENT_BOOST = REGISTRY.register("defense_percent_boost", DefensePercentBoostMobEffect::new);
    public static final RegistryObject<MobEffect> JUMP_PERCENT_BOOST = REGISTRY.register("jump_percent_boost", JumpPercentBoostMobEffect::new);
    public static final RegistryObject<MobEffect> BUFF_HASTE = REGISTRY.register("buff_haste", BuffHasteMobEffect::new);
    public static final RegistryObject<MobEffect> BUFF_IRON_WILL = REGISTRY.register("buff_iron_will", BuffIronWillMobEffect::new);
    public static final RegistryObject<MobEffect> BUFF_SOUL_ARROW = REGISTRY.register("buff_soul_arrow", BuffSoulArrowMobEffect::new);
    public static final RegistryObject<MobEffect> BUFF_RAGE = REGISTRY.register("buff_rage", BuffRageMobEffect::new);
    public static final RegistryObject<MobEffect> BUFF_COMBO_ATTACK = REGISTRY.register("buff_combo_attack", BuffComboAttackMobEffect::new);
}
