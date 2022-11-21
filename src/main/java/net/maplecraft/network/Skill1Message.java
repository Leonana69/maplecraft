package net.maplecraft.network;

import com.mojang.blaze3d.platform.InputConstants;
import net.maplecraft.MapleCraftMod;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Skill1Message {
    int type, duration;

    public Skill1Message(int type, int duration) {
        this.type = type;
        this.duration = duration;
    }

    public Skill1Message(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.duration = buffer.readInt();
    }

    public static void buffer(Skill1Message message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.type);
        buffer.writeInt(message.duration);
    }

    public static void handler(Skill1Message message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            pressAction(context.getSender(), message.type, message.duration);
        });
        context.setPacketHandled(true);
    }

    public static void pressAction(Player entity, int type, int duration) {
        Level world = entity.level;
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        // security measure to prevent arbitrary chunk generation
        if (!world.hasChunkAt(entity.blockPosition()))
            return;
        if (type == 0) {
            System.out.println("Key 1 pressed");
        } else {
            System.out.println("Key 1 released after " + duration + "ms");
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(Skill1Message.class, Skill1Message::buffer, Skill1Message::new, Skill1Message::handler);
    }
}
