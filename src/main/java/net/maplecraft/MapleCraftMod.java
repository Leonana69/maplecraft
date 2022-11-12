package net.maplecraft;

import net.maplecraft.init.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
    }
}
