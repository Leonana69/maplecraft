package com.maplecraft.init;

import com.maplecraft.entity.boss.zakum.BossZakumBodyEntity;
import com.maplecraft.entity.boss.zakum.BossZakumLeftHandEntity;
import com.maplecraft.entity.boss.zakum.BossZakumRightHandEntity;
import com.maplecraft.entity.monster.BlueSnailEntity;
import com.maplecraft.entity.monster.OrangeMushroomEntity;
import com.maplecraft.entity.monster.SlimeEntity;
import com.maplecraft.entity.projectile.*;
import com.maplecraft.entity.summon.HolyDragonEntity;
import com.maplecraft.MapleCraftMod;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntitiesInit {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MapleCraftMod.MODID);

    // projectile entity
    public static final RegistryObject<EntityType<SubiThrowingStarsEntity>> SUBI_THROWING_STARS_ENTITY = registerProjectileEntity(SubiThrowingStarsEntity::new, SubiThrowingStarsEntity.entityName, 0.3F, 0.2F);
    public static final RegistryObject<EntityType<IcicleEntity>> ICICLE_ENTITY = registerProjectileEntity(IcicleEntity::new, IcicleEntity.entityName, 0.3F, 0.3F);
    public static final RegistryObject<EntityType<SteelyThrowingKnivesEntity>> STEELY_THROWING_KNIVES_ENTITY = registerProjectileEntity(SteelyThrowingKnivesEntity::new, SteelyThrowingKnivesEntity.entityName, 0.3F, 0.3F);
    public static final RegistryObject<EntityType<BalancedFuryEntity>> BALANCED_FURY_ENTITY = registerProjectileEntity(BalancedFuryEntity::new, BalancedFuryEntity.entityName, 0.4F, 0.2F);
    public static final RegistryObject<EntityType<ArrowForBowEntity>> ARROW_FOR_BOW_ENTITY = registerProjectileEntity(ArrowForBowEntity::new, ArrowForBowEntity.entityName, 0.4F, 0.4F);
    public static final RegistryObject<EntityType<BronzeArrowForBowEntity>> BRONZE_ARROW_FOR_BOW_ENTITY = registerProjectileEntity(BronzeArrowForBowEntity::new, BronzeArrowForBowEntity.entityName, 0.4F, 0.4F);
    public static final RegistryObject<EntityType<SteelArrowForBowEntity>> STEEL_ARROW_FOR_BOW_ENTITY = registerProjectileEntity(SteelArrowForBowEntity::new, SteelArrowForBowEntity.entityName, 0.4F, 0.4F);
    public static final RegistryObject<EntityType<DiamondArrowForBowEntity>> DIAMOND_ARROW_FOR_BOW_ENTITY = registerProjectileEntity(DiamondArrowForBowEntity::new, DiamondArrowForBowEntity.entityName, 0.4F, 0.4F);
    public static final RegistryObject<EntityType<BombArrowEntity>> BOMB_ARROW_ENTITY = registerProjectileEntity(BombArrowEntity::new, BombArrowEntity.entityName, 0.4F, 0.4F);
    public static final RegistryObject<EntityType<HolyArrowEntity>> HOLY_ARROW_ENTITY = registerProjectileEntity(HolyArrowEntity::new, HolyArrowEntity.entityName, 0.4F, 0.4F);
    public static final RegistryObject<EntityType<FireArrowEntity>> FIRE_ARROW_ENTITY = registerProjectileEntity(FireArrowEntity::new, FireArrowEntity.entityName, 0.4F, 0.4F);
    public static final RegistryObject<EntityType<PoisonBraceEntity>> POISON_BRACE_ENTITY = registerProjectileEntity(PoisonBraceEntity::new, PoisonBraceEntity.entityName, 0.4F, 0.4F);
    public static final RegistryObject<EntityType<ElementCompositionFPEntity>> ELEMENT_COMPOSITION_FP_ENTITY = registerProjectileEntity(ElementCompositionFPEntity::new, ElementCompositionFPEntity.entityName, 0.4F, 0.4F);
    public static final RegistryObject<EntityType<ElementCompositionILEntity>> ELEMENT_COMPOSITION_IL_ENTITY = registerProjectileEntity(ElementCompositionILEntity::new, ElementCompositionILEntity.entityName, 0.4F, 0.4F);
    public static final RegistryObject<EntityType<AvengerEntity>> AVENGER_ENTITY = registerProjectileEntity(AvengerEntity::new, AvengerEntity.entityName, 1.2F, 0.3F);

    // living entity
    public static final RegistryObject<EntityType<BossZakumBodyEntity>> BOSS_ZAKUM_BODY_ENTITY = registerLivingEntity(BossZakumBodyEntity::new, BossZakumBodyEntity.entityName, MobCategory.MONSTER, 4.0F, 8.0F);
    public static final RegistryObject<EntityType<BossZakumLeftHandEntity>> BOSS_ZAKUM_LEFT_HAND_ENTITY = registerLivingEntity(BossZakumLeftHandEntity::new, BossZakumLeftHandEntity.entityName, MobCategory.MONSTER, 6.0F, 2.5F);
    public static final RegistryObject<EntityType<BossZakumRightHandEntity>> BOSS_ZAKUM_RIGHT_HAND_ENTITY = registerLivingEntity(BossZakumRightHandEntity::new, BossZakumRightHandEntity.entityName, MobCategory.MONSTER, 6.0F, 2.5F);

    public static final RegistryObject<EntityType<HolyDragonEntity>> HOLY_DRAGON_ENTITY = registerLivingEntity(HolyDragonEntity::new, HolyDragonEntity.entityName, MobCategory.MISC, 0.5F, 0.5F);
    public static final RegistryObject<EntityType<BlueSnailEntity>> BLUE_SNAIL_ENTITY = registerLivingEntity(BlueSnailEntity::new, BlueSnailEntity.entityName, MobCategory.MONSTER, 0.6F, 0.8F);
    public static final RegistryObject<EntityType<OrangeMushroomEntity>> ORANGE_MUSHROOM_ENTITY = registerLivingEntity(OrangeMushroomEntity::new, OrangeMushroomEntity.entityName, MobCategory.MONSTER, 1.3F, 1.6F);
    public static final RegistryObject<EntityType<SlimeEntity>> SLIME_ENTITY = registerLivingEntity(SlimeEntity::new, SlimeEntity.entityName, MobCategory.MONSTER, 1.3F, 1.6F);

    static <T extends Entity> RegistryObject<EntityType<T>> registerLivingEntity(EntityType.EntityFactory<T> entityFactory, String entityName, MobCategory mobCategory, float size, float height) {
        return REGISTRY.register(entityName,
                        () -> EntityType.Builder.of(entityFactory, mobCategory)
                                .sized(size, height)
                                .build(entityName));
    }

    static <T extends Entity> RegistryObject<EntityType<T>> registerProjectileEntity(EntityType.EntityFactory<T> entityFactory, String entityName, float size, float height) {
        return REGISTRY.register(entityName,
                () -> EntityType.Builder.<T>of(entityFactory, MobCategory.MISC)
                        .setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
                        .setUpdateInterval(1)
                        .sized(size, height)
                        .build(entityName));
    }
}
