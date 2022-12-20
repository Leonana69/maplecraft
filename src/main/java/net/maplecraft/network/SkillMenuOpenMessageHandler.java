package net.maplecraft.network;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.inventory.SkillMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkillMenuOpenMessageHandler {
    public SkillMenuOpenMessageHandler() {}
    public SkillMenuOpenMessageHandler(FriendlyByteBuf buffer) {}
    public static void buffer(SkillMenuOpenMessageHandler message, FriendlyByteBuf buffer) {}

    public static void handler(SkillMenuOpenMessageHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getSender() != null)
                action(context.getSender());
        });
        context.setPacketHandled(true);
    }

    public static void action(ServerPlayer serverPlayer) {
        BlockPos blockPos = new BlockPos(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ());
        NetworkHooks.openScreen(serverPlayer, SkillMenu.getServerMenu());
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(SkillMenuOpenMessageHandler.class, SkillMenuOpenMessageHandler::buffer, SkillMenuOpenMessageHandler::new, SkillMenuOpenMessageHandler::handler);
    }
}
