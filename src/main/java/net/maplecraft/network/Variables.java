package net.maplecraft.network;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.init.MobEffectsInit;
import net.maplecraft.utils.*;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static net.maplecraft.network.Variables.PlayerVariables.VARIABLE_COUNT;
import static net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Variables {
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        MapleCraftMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
                PlayerVariablesSyncMessage::handler);
    }

    @SubscribeEvent
    public static void init(RegisterCapabilitiesEvent event) {
        event.register(PlayerVariables.class);
    }

    @Mod.EventBusSubscriber
    public static class EventBusVariableHandlers {
        @SubscribeEvent
        public static void playerVariablesUpdate(TickEvent.PlayerTickEvent event) {
            List<ItemStack> list = event.player.getInventory().armor;
            List<PotentialStats> lp = new ArrayList<>();
            List<BaseStats> lb = new ArrayList<>();
            list.forEach(itemStack -> {
                if (itemStack.getItem() instanceof IBaseEquip baseEquip) {
                    if (baseEquip.hasPotential(itemStack)) {
                        EquipWiseData data = baseEquip.getEquipWiseData(itemStack);
                        lp.add(data.potentials[0]);
                        lp.add(data.potentials[1]);
                        lp.add(data.potentials[2]);
                    }
                    lb.add(baseEquip.getBaseEquipData().baseStats);
                }
            });

            Map<String, Integer> mapPotentials = PotentialStats.sum(lp);
            Map<String, Integer> mapBaseStats = BaseStats.sum(lb);

            mapBaseStats.forEach((k, v) -> mapPotentials.merge(k, v, Integer::sum));
//
//
//            if (bs.values[5] > 0)
//                event.player.addEffect(new MobEffectInstance(MobEffects.JUMP, 5, bs.values[5], false, true));
//

            if (mapPotentials.get("SPEED") > 0) {
                event.player.addEffect(new MobEffectInstance(
                        MobEffectsInit.EQUIP_SPEED_BOOST.get(),
                        5, // duration in tick
                        mapPotentials.get("SPEED") - 1,
                        true, true));
            }
            System.out.println("S: " + event.player.getAttribute(MOVEMENT_SPEED).getValue());
//            if (!event.player.level.isClientSide) {
//                System.out.println("S: " + event.player.getAttribute(MOVEMENT_SPEED).getValue());
//            } else {
//                System.out.println("C: " + event.player.getAttribute(MOVEMENT_SPEED).getValue());
//            }

//
//            event.player.getAttribute(MOVEMENT_SPEED).setBaseValue(0.1 + mapPotentials.get("SPEED") / 1000.0F);
//            event.player.getAttribute(MAX_HEALTH).setBaseValue(20 + mapPotentials.get("MAX HP"));
        }

        @SubscribeEvent
        public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
            if (!event.getEntity().level.isClientSide())
                event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())
                        .syncPlayerVariables(event.getEntity());
        }

        @SubscribeEvent
        public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
            if (!event.getEntity().level.isClientSide())
                event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())
                        .syncPlayerVariables(event.getEntity());
        }

        @SubscribeEvent
        public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
            if (!event.getEntity().level.isClientSide())
                event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())
                        .syncPlayerVariables(event.getEntity());
        }

        @SubscribeEvent
        public static void clonePlayer(PlayerEvent.Clone event) {
            event.getOriginal().revive();
            PlayerVariables original = event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
                    .orElse(new PlayerVariables());
            PlayerVariables clone = event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
                    .orElse(new PlayerVariables());

            // player persistent variables
            // clone.playerMaxHP = original.playerMaxHP;

            // player lifetime variables
            if (!event.isWasDeath()) {
                for (int i = 0; i < VARIABLE_COUNT; i++) {
                    clone.values.set(i, original.values.get(i));
                }
            }
        }
    }

    public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
    });

    @Mod.EventBusSubscriber
    private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
        @SubscribeEvent
        public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
                event.addCapability(new ResourceLocation("maplecraft", "player_variables"), new PlayerVariablesProvider());
        }

        private final PlayerVariables playerVariables = new PlayerVariables();
        private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
        }

        @Override
        public Tag serializeNBT() {
            return playerVariables.writeNBT();
        }

        @Override
        public void deserializeNBT(Tag nbt) {
            playerVariables.readNBT(nbt);
        }
    }

    public static class PlayerVariables {
        public static final int VARIABLE_COUNT = 3;
        public List<Object> values = Arrays.asList(new Object[] {
                MapleCraftConstants.MAX_PLAYER_MANA_POINTS,
                "",
                0.0F
        });
        public List<String> names = List.of(
                "playerManaPoints",
                "bonusStats",
                "v3");
        public List<String> types = List.of(
                "int",
                "string",
                "float");

        public void syncPlayerVariables(Entity entity) {
            if (entity instanceof ServerPlayer serverPlayer)
                MapleCraftMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
        }

        public Tag writeNBT() {
            CompoundTag nbt = new CompoundTag();

            for (int i = 0; i < VARIABLE_COUNT; i++) {
                switch (types.get(i)) {
                    case "int" -> nbt.putInt(names.get(i), (int) values.get(i));
                    case "double" -> nbt.putDouble(names.get(i), (double) values.get(i));
                    case "float" -> nbt.putFloat(names.get(i), (float) values.get(i));
                    case "string" -> nbt.putString(names.get(i), (String) values.get(i));
                }
            }

            return nbt;
        }

        public void readNBT(Tag Tag) {
            CompoundTag nbt = (CompoundTag) Tag;

            for (int i = 0; i < VARIABLE_COUNT; i++) {
                switch (types.get(i)) {
                    case "int" -> values.set(i, nbt.getInt(names.get(i)));
                    case "double" -> values.set(i, nbt.getDouble(names.get(i)));
                    case "float" -> values.set(i, nbt.getFloat(names.get(i)));
                    case "string" -> values.set(i, nbt.getString(names.get(i)));
                }
            }
        }
    }

    public static class PlayerVariablesSyncMessage {
        public PlayerVariables data;

        public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
            this.data = new PlayerVariables();
            this.data.readNBT(buffer.readNbt());
        }

        public PlayerVariablesSyncMessage(PlayerVariables data) {
            this.data = data;
        }

        public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
            buffer.writeNbt((CompoundTag) message.data.writeNBT());
        }

        public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> {
                if (!context.getDirection().getReceptionSide().isServer()) {
                    PlayerVariables variables = Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
                            .orElse(new PlayerVariables());

                    for (int i = 0; i < VARIABLE_COUNT; i++) {
                        variables.values.set(i, message.data.values.get(i));
                    }
                }
            });
            context.setPacketHandled(true);
        }
    }

    public static void set(LivingEntity entity, String variableName, Object value) {
        entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(c -> {
            c.values.set(c.names.indexOf(variableName), value);
            c.syncPlayerVariables(entity);
        });
    }

    public static Object get(LivingEntity entity, String variableName) {
        PlayerVariables v = (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
                .orElse(new Variables.PlayerVariables()));
        return v.values.get(v.names.indexOf(variableName));
    }
}
