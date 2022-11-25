package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.entities.*;
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
                            .setUpdateInterval(1).sized(0.3f, 0.1f)
                            .build("subi_throwing_stars_entity"));

    public static final RegistryObject<EntityType<SteelyThrowingKnivesEntity>> STEELY_THROWING_KNIVES_ENTITY = REGISTRY
            .register("steely_throwing_knives_entity",
                    () -> EntityType.Builder.<SteelyThrowingKnivesEntity>of(SteelyThrowingKnivesEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(SteelyThrowingKnivesEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.4f, 0.4f)
                            .build("steely_throwing_knives_entity"));

    public static final RegistryObject<EntityType<BalancedFuryEntity>> BALANCED_FURY_ENTITY = REGISTRY
            .register("balanced_fury_entity",
                    () -> EntityType.Builder.<BalancedFuryEntity>of(BalancedFuryEntity::new, MobCategory.MISC)
                            .setCustomClientFactory(BalancedFuryEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                            .setUpdateInterval(1).sized(0.4f, 0.1f)
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
}
