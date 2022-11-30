package net.maplecraft.procedures;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.entities.boss.zakum.BossZakumBodyEntity;
import net.maplecraft.init.EntitiesInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MapleCraftMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributeCreation {
    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(EntitiesInit.BOSS_ZAKUM_BODY_ENTITY.get(), BossZakumBodyEntity.setAttributes().build());
        event.put(EntitiesInit.BOSS_ZAKUM_HAND_ENTITY.get(), BossZakumBodyEntity.setAttributes().build());
    }
}
