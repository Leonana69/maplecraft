package net.maplecraft.network;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.utils.AllSkillList;
import net.maplecraft.item.SkillItem;
import net.maplecraft.item.WeaponBowItem;
import net.maplecraft.item.WeaponCrossbowItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SkillKeyPressMessageHandler {
    int type, duration;
    char key;

    public SkillKeyPressMessageHandler(int type, int duration, char key) {
        this.type = type;
        this.duration = duration;
        this.key = key;
    }

    public SkillKeyPressMessageHandler(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.duration = buffer.readInt();
        this.key = buffer.readChar();
    }

    public static void buffer(SkillKeyPressMessageHandler message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.type);
        buffer.writeInt(message.duration);
        buffer.writeChar(message.key);
    }

    public static void handler(SkillKeyPressMessageHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getSender() != null)
                pressAction(context.getSender(), message.type, message.duration, message.key);
        });
        context.setPacketHandled(true);
    }

    public static void pressAction(Player player, int type, int duration, char key) {
        if (!player.level.isClientSide) {
            ItemStack weapon = player.getMainHandItem();
            if (weapon.getItem() instanceof WeaponBowItem bow) {
                bow.setUsingAnime = type == 0;
            } else if (weapon.getItem() instanceof WeaponCrossbowItem crossbow) {
                crossbow.setUsingAnime = type == 0;
            }

            if (type == 1) {
                // released
                int skillID = (int) Variables.get(player, "skillID" + key);
                ItemLike skillItem = AllSkillList.SKILLS.get(skillID);
                if (skillItem != null) {
                    SkillItem skill = (SkillItem) skillItem.asItem();
                    if (skill.canUse(player)) {
                        skill.playerEffect(player);
                        skill.skillEffect(player);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(SkillKeyPressMessageHandler.class, SkillKeyPressMessageHandler::buffer, SkillKeyPressMessageHandler::new, SkillKeyPressMessageHandler::handler);
    }
}
