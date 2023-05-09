package com.maplecraft.procedures;

import com.maplecraft.entity.boss.zakum.BossZakumLeftHandEntity;
import com.maplecraft.entity.boss.zakum.BossZakumRightHandEntity;
import com.maplecraft.entity.monster.BlueSnailEntity;
import com.maplecraft.entity.monster.OrangeMushroomEntity;
import com.maplecraft.entity.MapleMobEntity;
import com.maplecraft.entity.monster.SlimeEntity;
import com.maplecraft.entity.summon.HolyDragonEntity;
import com.maplecraft.MapleCraftMod;
import com.maplecraft.entity.boss.zakum.BossZakumBodyEntity;
import com.maplecraft.init.EntitiesInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MapleCraftMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LivingEntityAttributeCreation {
    static <T extends MapleMobEntity> void registerSpawn(SpawnPlacementRegisterEvent event, EntityType<T> entityType) {
        event.register(entityType, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MapleMobEntity::checkMapleMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }

    @SubscribeEvent
    public static void registerEntity(SpawnPlacementRegisterEvent event) {
        registerSpawn(event, EntitiesInit.BLUE_SNAIL_ENTITY.get());
        registerSpawn(event, EntitiesInit.ORANGE_MUSHROOM_ENTITY.get());
        registerSpawn(event, EntitiesInit.SLIME_ENTITY.get());
    }

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(EntitiesInit.BOSS_ZAKUM_BODY_ENTITY.get(), BossZakumBodyEntity.setAttributes().build());
        event.put(EntitiesInit.BOSS_ZAKUM_LEFT_HAND_ENTITY.get(), BossZakumLeftHandEntity.setAttributes().build());
        event.put(EntitiesInit.BOSS_ZAKUM_RIGHT_HAND_ENTITY.get(), BossZakumRightHandEntity.setAttributes().build());
        event.put(EntitiesInit.HOLY_DRAGON_ENTITY.get(), HolyDragonEntity.setAttributes().build());

        event.put(EntitiesInit.BLUE_SNAIL_ENTITY.get(), BlueSnailEntity.setAttributes().build());
        event.put(EntitiesInit.ORANGE_MUSHROOM_ENTITY.get(), OrangeMushroomEntity.setAttributes().build());
        event.put(EntitiesInit.SLIME_ENTITY.get(), SlimeEntity.setAttributes().build());
    }
}
