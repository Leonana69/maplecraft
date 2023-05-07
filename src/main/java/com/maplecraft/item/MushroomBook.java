package com.maplecraft.item;

import com.maplecraft.init.TabsInit;
import com.maplecraft.inventory.MushroomBookViewScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.protocol.game.ClientboundOpenBookPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.level.Level;

public class MushroomBook extends WrittenBookItem {
    public static String itemName = "mushroom_book";
    public MushroomBook() {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT).stacksTo(1));
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        CompoundTag compoundTag= itemstack.getTag();
        if (!WrittenBookItem.makeSureTagIsValid(compoundTag)) {
            itemstack.addTagElement("title", StringTag.valueOf("MapleStory"));
            itemstack.addTagElement("author", StringTag.valueOf("Jansann"));
            ListTag listTag = new ListTag();
            listTag.add(StringTag.valueOf("{\"text\": \"    Skill\\n\\n\", \"bold\": true, \"hoverEvent\": {\"action\": \"show_text\", \"contents\": [{\"text\": \"Click the button on the left\"}]}," +
                    "\"extra\": [" +
                    "{\"text\": \"    Quest\\n\", \"hoverEvent\": {\"action\": \"show_text\", \"contents\": [{\"text\": \"Click the button on the left\"}]}}" +
                    "]}"));

            itemstack.addTagElement("pages", listTag);
        }

        if (player instanceof ServerPlayer serverPlayer) {
            if (WrittenBookItem.resolveBookComponents(itemstack, serverPlayer.createCommandSourceStack(), serverPlayer)) {
                serverPlayer.containerMenu.broadcastChanges();
            }
        } else {
            Minecraft.getInstance().setScreen(new MushroomBookViewScreen(itemstack));
        }
        return InteractionResultHolder.sidedSuccess(itemstack, world.isClientSide());
    }
}
