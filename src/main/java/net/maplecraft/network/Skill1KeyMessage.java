package net.maplecraft.network;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.utils.AllSkillList;
import net.maplecraft.utils.EquipCategory;
import net.maplecraft.utils.IBaseEquip;
import net.maplecraft.utils.SkillItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Skill1KeyMessage {
    int type, duration;

    public Skill1KeyMessage(int type, int duration) {
        this.type = type;
        this.duration = duration;
    }

    public Skill1KeyMessage(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.duration = buffer.readInt();
    }

    public static void buffer(Skill1KeyMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.type);
        buffer.writeInt(message.duration);
    }

    public static void handler(Skill1KeyMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            pressAction(context.getSender(), message.type, message.duration);
        });
        context.setPacketHandled(true);
    }

    public static void pressAction(Player player, int type, int duration) {
        if (type == 0) {
            System.out.println("Key 1 pressed");
        } else {
            System.out.println("Key 1 released after " + duration + "ms");
        }

        if (type == 0) {
            int skillID = (int) Variables.get(player, "skillID0");
            ItemLike skillItem = AllSkillList.SKILLS.get(skillID);
            if (skillItem != null) {
                SkillItem skill = (SkillItem) skillItem.asItem();
                if (skill.canUse(player)) {
                    skill.skillEffect(player);
                    skill.postEffect(player);
                }
            }
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(Skill1KeyMessage.class, Skill1KeyMessage::buffer, Skill1KeyMessage::new, Skill1KeyMessage::handler);
    }
}
