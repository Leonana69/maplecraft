package com.maplecraft.network;

import com.maplecraft.client.screens.CubeScreen;
import com.maplecraft.inventory.CubeMenu;
import com.maplecraft.item.use.UseBlackCubeItem;
import com.maplecraft.MapleCraftMod;
import com.maplecraft.item.ScrollItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CubeMenuSlotMessageHandler {
    private final int slotID;

    public CubeMenuSlotMessageHandler(int slotID) {
        this.slotID = slotID;
    }

    public CubeMenuSlotMessageHandler(FriendlyByteBuf buffer) {
        this.slotID = buffer.readInt();
    }

    public static void buffer(CubeMenuSlotMessageHandler message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.slotID);
    }

    public static void handler(CubeMenuSlotMessageHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getSender() != null) {
                handleSlotAction(context.getSender(), message.slotID);
            }
        });
        context.setPacketHandled(true);
    }

    public static void handleSlotAction(Player player, int slotID) {
        if (player.containerMenu instanceof CubeMenu cubeMenu) {
            ItemStack itemStack = cubeMenu.get().get(0).getItem();

            if (player.level.isClientSide)
                CubeScreen.showPotentialText(cubeMenu, itemStack);

            if (slotID == 0) {
                cubeMenu.updated = false;
            }

            // update GUI based on cube type
            if (slotID == 1) {
                ItemStack itemStack1 = cubeMenu.get().get(1).getItem();
                if (itemStack1.getItem() instanceof UseBlackCubeItem) {
                    cubeMenu.guiType = 1;
                } else if (itemStack1.getItem() instanceof ScrollItem) {
                    cubeMenu.guiType = 2;
                } else if (!itemStack1.isEmpty()) {
                    cubeMenu.guiType = 0;
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(CubeMenuSlotMessageHandler.class, CubeMenuSlotMessageHandler::buffer, CubeMenuSlotMessageHandler::new, CubeMenuSlotMessageHandler::handler);
    }
}
