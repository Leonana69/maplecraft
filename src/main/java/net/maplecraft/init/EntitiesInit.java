package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.entities.*;
import net.maplecraft.entities.boss.zakum.BossZakumBodyEntity;
import net.maplecraft.entities.boss.zakum.BossZakumLeftHandEntity;
import net.maplecraft.entities.boss.zakum.BossZakumRightHandEntity;
import net.maplecraft.entities.summon.holyDragon.HolyDragonEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntitiesInit {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MapleCraftMod.MODID);

    public static final RegistryObject<EntityType<SubiThrowingStarsEntity>> SUBI_THROWING_STARS_ENTITY = REGISTRY
            .register("subi_throwing_stars_entity",
                    () -> EntityType.Builder.<SubiThrowingStarsEntity>of(SubiThrowingStarsEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(SubiThrowingStarsEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.3f, 0.2f)
                            .build("subi_throwing_stars_entity"));

    public static final RegistryObject<EntityType<IcicleEntity>> ICICLE_ENTITY = REGISTRY
            .register("icicle_entity",
                    () -> EntityType.Builder.<IcicleEntity>of(IcicleEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(IcicleEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.3f, 0.3f)
                            .build("icicle_entity"));

    public static final RegistryObject<EntityType<SteelyThrowingKnivesEntity>> STEELY_THROWING_KNIVES_ENTITY = REGISTRY
            .register("steely_throwing_knives_entity",
                    () -> EntityType.Builder.<SteelyThrowingKnivesEntity>of(SteelyThrowingKnivesEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(SteelyThrowingKnivesEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.3f, 0.3f)
                            .build("steely_throwing_knives_entity"));

    public static final RegistryObject<EntityType<BalancedFuryEntity>> BALANCED_FURY_ENTITY = REGISTRY
            .register("balanced_fury_entity",
                    () -> EntityType.Builder.<BalancedFuryEntity>of(BalancedFuryEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(BalancedFuryEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.4f, 0.2f)
                            .build("balanced_fury_entity"));

    public static final RegistryObject<EntityType<ArrowForBowEntity>> ARROW_FOR_BOW_ENTITY = REGISTRY
            .register("arrow_for_bow_entity",
                    () -> EntityType.Builder.<ArrowForBowEntity>of(ArrowForBowEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(ArrowForBowEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.4f, 0.4f)
                            .build("arrow_for_bow_entity"));

    public static final RegistryObject<EntityType<BronzeArrowForBowEntity>> BRONZE_ARROW_FOR_BOW_ENTITY = REGISTRY
            .register("bronze_arrow_for_bow_entity",
                    () -> EntityType.Builder.<BronzeArrowForBowEntity>of(BronzeArrowForBowEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(BronzeArrowForBowEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.4f, 0.4f)
                            .build("bronze_arrow_for_bow_entity"));

    public static final RegistryObject<EntityType<SteelArrowForBowEntity>> STEEL_ARROW_FOR_BOW_ENTITY = REGISTRY
            .register("steel_arrow_for_bow_entity",
                    () -> EntityType.Builder.<SteelArrowForBowEntity>of(SteelArrowForBowEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(SteelArrowForBowEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.4f, 0.4f)
                            .build("steel_arrow_for_bow_entity"));

    public static final RegistryObject<EntityType<DiamondArrowForBowEntity>> DIAMOND_ARROW_FOR_BOW_ENTITY = REGISTRY
            .register("diamond_arrow_for_bow_entity",
                    () -> EntityType.Builder.<DiamondArrowForBowEntity>of(DiamondArrowForBowEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(DiamondArrowForBowEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.4f, 0.4f)
                            .build("diamond_arrow_for_bow_entity"));

    public static final RegistryObject<EntityType<BombArrowEntity>> BOMB_ARROW_ENTITY = REGISTRY
            .register("bomb_arrow_entity",
                    () -> EntityType.Builder.<BombArrowEntity>of(BombArrowEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(BombArrowEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.4f, 0.4f)
                            .build("bomb_arrow_entity"));

    public static final RegistryObject<EntityType<HolyArrowEntity>> HOLY_ARROW_ENTITY = REGISTRY
            .register("holy_arrow_entity",
                    () -> EntityType.Builder.<HolyArrowEntity>of(HolyArrowEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(HolyArrowEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.4f, 0.4f)
                            .build("holy_arrow_entity"));

    public static final RegistryObject<EntityType<FireArrowEntity>> FIRE_ARROW_ENTITY = REGISTRY
            .register("fire_arrow_entity",
                    () -> EntityType.Builder.<FireArrowEntity>of(FireArrowEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(FireArrowEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.4f, 0.4f)
                            .build("fire_arrow_entity"));

    public static final RegistryObject<EntityType<PoisonBraceEntity>> POISON_BRACE_ENTITY = REGISTRY
            .register("poison_brace_entity",
                    () -> EntityType.Builder.<PoisonBraceEntity>of(PoisonBraceEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(PoisonBraceEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.4f, 0.4f)
                            .build("poison_brace_entity"));

    public static final RegistryObject<EntityType<ElementCompositionFPEntity>> ELEMENT_COMPOSITION_FP_ENTITY = REGISTRY
            .register("element_composition_fp_entity",
                    () -> EntityType.Builder.<ElementCompositionFPEntity>of(ElementCompositionFPEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(ElementCompositionFPEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.4f, 0.4f)
                            .build("element_composition_fp_entity"));

    public static final RegistryObject<EntityType<ElementCompositionILEntity>> ELEMENT_COMPOSITION_IL_ENTITY = REGISTRY
            .register("element_composition_il_entity",
                    () -> EntityType.Builder.<ElementCompositionILEntity>of(ElementCompositionILEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(ElementCompositionILEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.4f, 0.4f)
                            .build("element_composition_il_entity"));

    public static final RegistryObject<EntityType<AvengerEntity>> AVENGER_ENTITY = REGISTRY
            .register("avenger_entity",
                    () -> EntityType.Builder.<AvengerEntity>of(AvengerEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(AvengerEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(1.2f, 0.3f)
                            .build("avenger_entity"));

    // living entity
    public static final RegistryObject<EntityType<BossZakumBodyEntity>> BOSS_ZAKUM_BODY_ENTITY = REGISTRY
            .register("boss_zakum_body_entity",
                    () -> EntityType.Builder.of(BossZakumBodyEntity::new, MobCategory.MONSTER)
                            .sized(4.0f, 5.5f)
                            .build("boss_zakum_body_entity"));

    public static final RegistryObject<EntityType<BossZakumLeftHandEntity>> BOSS_ZAKUM_LEFT_HAND_ENTITY = REGISTRY
            .register("boss_zakum_left_hand_entity",
                    () -> EntityType.Builder.of(BossZakumLeftHandEntity::new, MobCategory.MONSTER)
                            .sized(3.0f, 1.5f)
                            .build("boss_zakum_left_hand_entity"));

    public static final RegistryObject<EntityType<BossZakumRightHandEntity>> BOSS_ZAKUM_RIGHT_HAND_ENTITY = REGISTRY
            .register("boss_zakum_right_hand_entity",
                    () -> EntityType.Builder.of(BossZakumRightHandEntity::new, MobCategory.MONSTER)
                            .sized(3.0f, 1.5f)
                            .build("boss_zakum_right_hand_entity"));

    public static final RegistryObject<EntityType<HolyDragonEntity>> HOLY_DRAGON_ENTITY = REGISTRY
            .register("holy_dragon_entity",
                    () -> EntityType.Builder.of(HolyDragonEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            .build("holy_dragon_entity"));
}
