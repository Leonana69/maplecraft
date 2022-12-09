package net.maplecraft.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.maplecraft.init.ItemsInit;
import net.maplecraft.item.use.UseJobAdvancementCoinItem;
import net.maplecraft.network.Variables;
import net.maplecraft.utils.JobCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber
public class SetJobCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("setJob").requires(s -> s.hasPermission(1))
                .then(Commands.argument("job", IntegerArgumentType.integer(0)).executes(arguments -> {
                    int job = arguments.getArgument("job", Integer.class);

                    if (arguments.getSource().getEntity() instanceof ServerPlayer player) {
                        String message = Component.translatable("utils.maplecraft.job_advance_success").getString();
                        message += "[" + JobCategory.getJob(job).typeName + "]";
                        player.displayClientMessage(Component.literal(message).withStyle(ChatFormatting.GOLD), false);

                        if (!player.getAbilities().instabuild && job > 0) {
                            ItemStack itemStack = player.getMainHandItem();
                            if (itemStack.getItem() instanceof UseJobAdvancementCoinItem) {
                                itemStack.shrink(1);
                            } else {
                                player.displayClientMessage(Component.translatable("utils.maplecraft.job_advance_no_coin").withStyle(ChatFormatting.BLACK), false);
                                return 0;
                            }
                        }
                        Variables.set((LivingEntity) Objects.requireNonNull(arguments.getSource().getEntity()), "jobType", job);

                        if (job <= 4 && job >= 1) {
                            ItemStack weapon = ItemStack.EMPTY;
                            switch (job) {
                                case 1 -> weapon = new ItemStack(ItemsInit.SWORD_SWORD.get());
                                case 2 -> weapon = new ItemStack(ItemsInit.WAND_HARDWOOD_WAND.get());
                                case 3 -> weapon = new ItemStack(ItemsInit.BOW_WAR_BOW.get());
                                case 4 -> weapon = new ItemStack(ItemsInit.CLAW_GARNIER.get());
                            }
                            ItemEntity entityToSpawn = new ItemEntity(arguments.getSource().getLevel(), player.getX(), player.getY(), player.getZ(),
                                    weapon);
                            entityToSpawn.setPickUpDelay(10);
                            arguments.getSource().getLevel().addFreshEntity(entityToSpawn);
                        }
                    }
                    return 0;
                })));
    }
}

