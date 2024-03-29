package com.maplecraft;

import com.maplecraft.client.overlay.GeneralBarOverlay;
import com.maplecraft.procedures.*;
import com.maplecraft.procedures.AddSkillSlotOverlay;
import com.maplecraft.init.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import software.bernie.geckolib3.GeckoLib;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod(MapleCraftMod.MODID)
public class MapleCraftMod {
    public static final String MODID = "maplecraft";

    public MapleCraftMod() {
        TabsInit.load();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        SoundsInit.REGISTRY.register(bus);
        ItemsInit.REGISTRY.register(bus);
        EntitiesInit.REGISTRY.register(bus);
        ParticlesTypeInit.REGISTRY.register(bus);
        MenusInit.REGISTRY.register(bus);
        LootModifiersInit.REGISTRY.register(bus);
        EffectsInit.REGISTRY.register(bus);
        BlocksInit.REGISTRY.register(bus);
        FeaturesInit.REGISTRY.register(bus);

        if (FMLEnvironment.dist.isClient()) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInitClient);
        }

        GeckoLib.initialize();
    }

    private void preInitClient(final FMLClientSetupEvent event) {
        GeneralBarOverlay.init();
        MapleItemTooltip.init();
        AddMapleButton.init();
        AttackSoundGenerator.init();
        SkillEffectRender.init();
        AddSkillSlotOverlay.init();
    }

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, MODID), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder,
                                             BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }

    private static final List<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ArrayList<>();

    public static void queueServerWork(int tick, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry(action, tick));
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
            workQueue.forEach(work -> {
                work.setValue(work.getValue() - 1);
                if (work.getValue() == 0)
                    actions.add(work);
            });
            actions.forEach(e -> e.getKey().run());
            workQueue.removeAll(actions);
        }
    }
}
