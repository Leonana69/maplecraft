package com.maplecraft.item.use;

import com.maplecraft.item.MapleItem;
import com.maplecraft.item.MapleItemProperties;
import com.maplecraft.network.Variables;
import com.maplecraft.utils.JobCategory;
import com.maplecraft.utils.MapleRarity;
import com.maplecraft.init.TabsInit;
import com.maplecraft.utils.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static net.minecraft.network.chat.ClickEvent.Action.RUN_COMMAND;

public class UseJobAdvancementCoinItem extends MapleItem {
    public static String itemName = "use_job_advancement_coin";
    public UseJobAdvancementCoinItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.EPIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            JobCategory jobType = JobCategory.getJob((int) Variables.get(player, "jobType"));
            MutableComponent jobOptions = Component.literal("");
            ItemStack itemstack = player.getItemInHand(hand);
            if (jobType.advancement < 3) {
                for (JobCategory value : JobCategory.VALUES) {
                    if (value.advancement == jobType.advancement + 1 && jobType.isSuccessor(value)) {
                        MutableComponent component = Component.literal("[" + value.typeName + "] ").withStyle(ChatFormatting.GREEN);
                        component.setStyle(component.getStyle().withUnderlined(true).withClickEvent(new ClickEvent(RUN_COMMAND, "/SetJob " + value.type)));
                        jobOptions.append(component);
                    }
                }
                serverPlayer.displayClientMessage(jobOptions, false);
                return InteractionResultHolder.consume(itemstack);
            } else {
                serverPlayer.displayClientMessage(Component.translatable("utils.maplecraft.job_advance_failed"), false);
                return InteractionResultHolder.pass(player.getItemInHand(hand));
            }
        }
        return super.use(world, player, hand);
    }
}
