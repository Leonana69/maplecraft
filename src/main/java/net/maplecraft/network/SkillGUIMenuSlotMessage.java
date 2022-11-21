package net.maplecraft.network;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.utils.SkillItem;
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

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkillGUIMenuSlotMessage {
    private final int slotID;

    public SkillGUIMenuSlotMessage(int slotID) {
        this.slotID = slotID;
    }

    public SkillGUIMenuSlotMessage(FriendlyByteBuf buffer) {
        this.slotID = buffer.readInt();
    }

    public static void buffer(SkillGUIMenuSlotMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.slotID);
    }

    public static void handler(SkillGUIMenuSlotMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            handleSlotAction(context.getSender(), message.slotID);
        });
        context.setPacketHandled(true);
    }

    public static void handleSlotAction(Player player, int slotID) {
        if (player.containerMenu instanceof Supplier supplier && supplier.get() instanceof Map slots) {
            System.out.println("slot action: " + slotID + ", " + ((Slot) slots.get(slotID)).getItem().getItem().getName(((Slot) slots.get(slotID)).getItem()));
            if (slotID < 4) {
                String variableName = "skillID" + slotID;

                if (((Slot) slots.get(slotID)).getItem().getItem() instanceof SkillItem skill) {
                    Variables.set(player, variableName, skill.skillBaseData.skillID);
                } else {
                    Variables.set(player, variableName, 0);
                }
            }
        }

    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(SkillGUIMenuSlotMessage.class, SkillGUIMenuSlotMessage::buffer, SkillGUIMenuSlotMessage::new, SkillGUIMenuSlotMessage::handler);
    }
}
