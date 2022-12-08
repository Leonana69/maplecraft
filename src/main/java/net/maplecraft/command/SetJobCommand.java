package net.maplecraft.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.maplecraft.network.Variables;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber
public class SetJobCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("setJob")
                .then(Commands.argument("job", IntegerArgumentType.integer(0)).executes(arguments -> {
                    if (arguments.getSource().getEntity() instanceof Player player && player.getAbilities().instabuild) {
                        int job = arguments.getArgument("job", Integer.class);
                        Variables.set((LivingEntity) Objects.requireNonNull(arguments.getSource().getEntity()), "jobType", job);
                    }
                    return 0;
                })));
    }
}

