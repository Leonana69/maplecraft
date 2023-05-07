package com.maplecraft.procedures;

import com.maplecraft.entity.boss.zakum.BossZakumLeftHandEntity;
import com.maplecraft.entity.boss.zakum.BossZakumRightHandEntity;
import com.maplecraft.entity.mobs.BlueSnailEntity.BlueSnailEntity;
import com.maplecraft.entity.mobs.MapleMobEntity;
import com.maplecraft.entity.summon.holyDragon.HolyDragonEntity;
import com.maplecraft.MapleCraftMod;
import com.maplecraft.entity.boss.zakum.BossZakumBodyEntity;
import com.maplecraft.init.EntitiesInit;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = MapleCraftMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributeCreation {
    @SubscribeEvent
    public static void registerEntity(SpawnPlacementRegisterEvent event) {
        event.register(EntitiesInit.BLUE_SNAIL_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MapleMobEntity::checkMapleMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(EntitiesInit.BOSS_ZAKUM_BODY_ENTITY.get(), BossZakumBodyEntity.setAttributes().build());
        event.put(EntitiesInit.BOSS_ZAKUM_LEFT_HAND_ENTITY.get(), BossZakumLeftHandEntity.setAttributes().build());
        event.put(EntitiesInit.BOSS_ZAKUM_RIGHT_HAND_ENTITY.get(), BossZakumRightHandEntity.setAttributes().build());
        event.put(EntitiesInit.HOLY_DRAGON_ENTITY.get(), HolyDragonEntity.setAttributes().build());
        event.put(EntitiesInit.BLUE_SNAIL_ENTITY.get(), BlueSnailEntity.setAttributes().build());
    }
}
