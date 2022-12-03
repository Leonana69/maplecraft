package net.maplecraft.network;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.client.screens.CubeScreen;
import net.maplecraft.item.use.UseBlackCubeItem;
import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.ScrollItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.Map;
import java.util.function.Supplier;

import static net.maplecraft.client.screens.CubeScreen.showPotentialText;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CubeScreenSlotMessageHandler {
    private final int slotID;

    public CubeScreenSlotMessageHandler(int slotID) {
        this.slotID = slotID;
    }

    public CubeScreenSlotMessageHandler(FriendlyByteBuf buffer) {
        this.slotID = buffer.readInt();
    }

    public static void buffer(CubeScreenSlotMessageHandler message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.slotID);
    }

    public static void handler(CubeScreenSlotMessageHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getSender() != null)
                handleSlotAction(context.getSender(), message.slotID);
        });
        context.setPacketHandled(true);
    }

    public static void handleSlotAction(Player player, int slotID) {
        if (player.level.isClientSide) {
            if (player.containerMenu instanceof Supplier supplier && supplier.get() instanceof Map slots) {
                ItemStack itemStack = ((Slot) slots.get(0)).getItem();
                showPotentialText(itemStack);

                if (slotID == 0) {
                    CubeItem.updated = false;
                }

                // update GUI based on cube type
                if (slotID == 1) {
                    ItemStack itemStack1 = ((Slot) slots.get(1)).getItem();
                    if (itemStack1.getItem() instanceof UseBlackCubeItem) {
                        CubeScreen.guiType = 1;
                    } else if (itemStack1.getItem() instanceof ScrollItem) {
                        CubeScreen.guiType = 2;
                    } else if (!itemStack1.isEmpty()) {
                        CubeScreen.guiType = 0;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(CubeScreenSlotMessageHandler.class, CubeScreenSlotMessageHandler::buffer, CubeScreenSlotMessageHandler::new, CubeScreenSlotMessageHandler::handler);
    }
}
