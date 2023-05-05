package com.maplecraft.command;

import com.maplecraft.network.Variables;
import com.maplecraft.utils.AllQuestList;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber
public class SetQuestStateCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("SetQuestState")
                .then(Commands.argument("state", StringArgumentType.string()).executes(arguments -> {
                    ServerLevel world = arguments.getSource().getLevel();
                    String state = arguments.getArgument("state", String.class);
                    if (state.equals("r"))
                        // restore to default state
                        Variables.set((LivingEntity) Objects.requireNonNull(arguments.getSource().getEntity()), "questState", AllQuestList.DEFAULT_STATE);
                    else
                        Variables.set((LivingEntity) Objects.requireNonNull(arguments.getSource().getEntity()), "questState", state);
                    return 0;
                })));
    }
}

