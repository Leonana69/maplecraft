package net.maplecraft.network;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.utils.IBaseEquip;
import net.maplecraft.utils.CubeItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CubeGUIMenuButtonMessage {
    private final int buttonID, x, y, z;

    public CubeGUIMenuButtonMessage(FriendlyByteBuf buffer) {
        this.buttonID = buffer.readInt();
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
    }

    public CubeGUIMenuButtonMessage(int buttonID, int x, int y, int z) {
        this.buttonID = buttonID;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void buffer(CubeGUIMenuButtonMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.buttonID);
        buffer.writeInt(message.x);
        buffer.writeInt(message.y);
        buffer.writeInt(message.z);
    }

    public static void handler(CubeGUIMenuButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Player entity = context.getSender();
            int buttonID = message.buttonID;
            handleButtonAction(entity, buttonID, message.x, message.y, message.z);
        });
        context.setPacketHandled(true);
    }
    public static void handleButtonAction(Player player, int buttonID, int x, int y, int z) {
        Level world = player.level;
//        // security measure to prevent arbitrary chunk generation
//        if (!world.hasChunkAt(new BlockPos(x, y, z)))
//            return;
        if (buttonID == 0) {
            if (player.containerMenu instanceof Supplier supplier && supplier.get() instanceof  Map slots) {
                // slot one is cube or scroll
                if (((Slot) slots.get(1)).getItem().getItem() instanceof CubeItem cube &&
                        ((Slot) slots.get(0)).getItem().getItem() instanceof IBaseEquip baseEquip) {
                    cube.execute(player, slots, baseEquip);
                } else {
                    if (!player.level.isClientSide())
                        player.displayClientMessage(Component.literal("Missing Items"), (false));
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(CubeGUIMenuButtonMessage.class, CubeGUIMenuButtonMessage::buffer, CubeGUIMenuButtonMessage::new,
                CubeGUIMenuButtonMessage::handler);
    }
}
