package net.maplecraft.procedures;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.entities.boss.zakum.BossZakumBodyEntity;
import net.maplecraft.entities.boss.zakum.BossZakumLeftHandEntity;
import net.maplecraft.entities.boss.zakum.BossZakumRightHandEntity;
import net.maplecraft.entities.summon.holyDragon.HolyDragonEntity;
import net.maplecraft.init.EntitiesInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MapleCraftMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributeCreation {
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(EntitiesInit.BOSS_ZAKUM_BODY_ENTITY.get(), BossZakumBodyEntity.setAttributes().build());
        event.put(EntitiesInit.BOSS_ZAKUM_LEFT_HAND_ENTITY.get(), BossZakumLeftHandEntity.setAttributes().build());
        event.put(EntitiesInit.BOSS_ZAKUM_RIGHT_HAND_ENTITY.get(), BossZakumRightHandEntity.setAttributes().build());
        event.put(EntitiesInit.HOLY_DRAGON_ENTITY.get(), HolyDragonEntity.setAttributes().build());
    }
}
