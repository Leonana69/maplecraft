package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.entities.SteelyThrowingKnivesEntity;
import net.maplecraft.entities.SubiThrowingStarsEntity;
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
}
