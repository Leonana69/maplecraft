package net.maplecraft.network;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.inventory.QuestMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class QuestScreenButtonMessageHandler {
    private final int questID;
    private final int tabID;
    public QuestScreenButtonMessageHandler(FriendlyByteBuf buffer) {
        this.questID = buffer.readInt();
        this.tabID = buffer.readInt();
    }

    public QuestScreenButtonMessageHandler(int questID, int tabID) {
        this.questID = questID;
        this.tabID = tabID;
    }

    public static void buffer(QuestScreenButtonMessageHandler message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.questID);
        buffer.writeInt(message.tabID);
    }

    public static void handler(QuestScreenButtonMessageHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getSender() != null)
                handleButtonAction(context.getSender(), message.questID, message.tabID);
        });
        context.setPacketHandled(true);
    }
    public static void handleButtonAction(Player player, int questID, int tabID) {
        if (!player.level.isClientSide && player.containerMenu instanceof QuestMenu menu) {
            QuestMenu.interact(menu, questID, tabID);
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(QuestScreenButtonMessageHandler.class, QuestScreenButtonMessageHandler::buffer, QuestScreenButtonMessageHandler::new,
                QuestScreenButtonMessageHandler::handler);
    }
}
