package net.maplecraft.network;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.client.screens.CubeGUIMenuScreen;
import net.maplecraft.items.UseBlackCubeItem;
import net.maplecraft.utils.CubeItem;
import net.maplecraft.utils.IBaseEquip;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.Map;
import java.util.function.Supplier;

import static net.maplecraft.client.screens.CubeGUIMenuScreen.showPotentialText;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CubeGUIMenuSlotMessage {
    private final int slotID;

    public CubeGUIMenuSlotMessage(int slotID) {
        this.slotID = slotID;
    }

    public CubeGUIMenuSlotMessage(FriendlyByteBuf buffer) {
        this.slotID = buffer.readInt();
    }

    public static void buffer(CubeGUIMenuSlotMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.slotID);
    }

    public static void handler(CubeGUIMenuSlotMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            handleSlotAction(context.getSender(), message.slotID);
        });
        context.setPacketHandled(true);
    }

    public static void handleSlotAction(Player player, int slotID) {
        if (player.level.isClientSide && slotID == 0) {
            if (player.containerMenu instanceof Supplier supplier && supplier.get() instanceof Map slots) {
                // slot one is cube or scroll
                if (((Slot) slots.get(slotID)).getItem().getItem() instanceof IBaseEquip) {
                    showPotentialText(((Slot) slots.get(slotID)).getItem(), true);
                } else {
                    showPotentialText(null, false);
                }
            }
        } else if (player.level.isClientSide && slotID == 1) {
            if (player.containerMenu instanceof Supplier supplier && supplier.get() instanceof Map slots) {
                // slot one is cube or scroll
                if (((Slot) slots.get(slotID)).getItem().getItem() instanceof UseBlackCubeItem) {
//                    showPotentialText(((Slot) slots.get(slotID)).getItem(), true);
                    CubeGUIMenuScreen.guiType = 1;
                } else {
                    CubeGUIMenuScreen.guiType = 0;
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(CubeGUIMenuSlotMessage.class, CubeGUIMenuSlotMessage::buffer, CubeGUIMenuSlotMessage::new, CubeGUIMenuSlotMessage::handler);
    }
}
