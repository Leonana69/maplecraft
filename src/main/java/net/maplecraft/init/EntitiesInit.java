package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.entities.BalancedFuryEntity;
import net.maplecraft.entities.SteelyThrowingKnivesEntity;
import net.maplecraft.entities.SubiThrowingStarsEntity;
import net.maplecraft.entities.ArrowForBowEntity;
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
}
