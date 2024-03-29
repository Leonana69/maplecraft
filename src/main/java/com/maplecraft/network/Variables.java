package com.maplecraft.network;

import com.maplecraft.MapleCraftMod;
import com.maplecraft.effect.DefensePercentBoostMobEffect;
import com.maplecraft.effect.JumpPercentBoostMobEffect;
import com.maplecraft.init.EffectsInit;
import com.maplecraft.item.WeaponItem;
import com.maplecraft.utils.*;
import com.maplecraft.utils.*;
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
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.*;
import java.util.function.Supplier;

import static com.maplecraft.network.Variables.PlayerVariables.VARIABLE_COUNT;
import static com.maplecraft.utils.AllQuestList.DEFAULT_STATE;

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
            Player player = event.player;

            // update skill cooldown
            for (int i = 0 ; i < 4; i++) {
                String valName = "skillCd" + i;
                float cooldown = (float) Variables.get(player, valName) - 0.05F;
                Variables.set(player, valName, Math.max(cooldown, 0.0F));
            }

            // recover mana by 0.5 for every 4s (80 ticks)
            double mana = (double) Variables.get(player, "playerManaPoints") + 0.5;
            if (!player.level.isClientSide
                    && mana <= MapleCraftConstants.MAX_PLAYER_MANA_POINTS
                    && player.level.getGameTime() % 80 == 0) {
                Variables.set(player, "playerManaPoints", mana);
            }

            // armor
            List<ItemStack> list = player.getInventory().armor;
            List<PotentialStats> lp = new ArrayList<>();
            List<BaseStats> lb = new ArrayList<>();
            list.forEach(itemStack -> {
                if (itemStack.getItem() instanceof IBaseEquip baseEquip && baseEquip.meetLevelReq(player)) {
                    if (baseEquip.hasPotential(itemStack)) {
                        EquipWiseData data = baseEquip.getEquipWiseData(itemStack);
                        lp.add(data.potentials[0]);
                        lp.add(data.potentials[1]);
                        lp.add(data.potentials[2]);
                    }
                    lb.add(baseEquip.getBaseEquipData().baseStats);
                }
            });

            // main hand
            ItemStack mainHandItem = player.getMainHandItem();
            if (mainHandItem.getItem() instanceof WeaponItem weapon && weapon.meetLevelReq(player)) {
                if (weapon.hasPotential(mainHandItem)) {
                    EquipWiseData data = weapon.getEquipWiseData(mainHandItem);
                    lp.add(data.potentials[0]);
                    lp.add(data.potentials[1]);
                    lp.add(data.potentials[2]);
                }
                lb.add(weapon.getBaseEquipData().baseStats);
            }

            // curios
            Optional<IItemHandlerModifiable> itemHandlerOptional = CuriosApi.getCuriosHelper().getEquippedCurios(player).resolve();
            if (itemHandlerOptional.isPresent()) {
                IItemHandlerModifiable itemHandler = itemHandlerOptional.get();
                for (int i = 0; i < itemHandler.getSlots(); i++) {
                    ItemStack itemStack = itemHandler.getStackInSlot(i);
                    if (!itemStack.isEmpty()
                            && itemStack.getItem() instanceof IBaseEquip baseEquip && baseEquip.meetLevelReq(player)) {
                        if (baseEquip.hasPotential(itemStack)) {
                            EquipWiseData data = baseEquip.getEquipWiseData(itemStack);
                            lp.add(data.potentials[0]);
                            lp.add(data.potentials[1]);
                            lp.add(data.potentials[2]);
                        }
                        lb.add(baseEquip.getBaseEquipData().baseStats);
                    }
                }
            }

            Map<String, Integer> mapPotentials = PotentialStats.sum(lp);
            Map<String, Integer> mapBaseStats = BaseStats.sum(lb);

//            if (player.tickCount % 20 == 0) {
//                System.out.println("Potentials: " + mapPotentials);
//                System.out.println("BaseStats: " + mapBaseStats);
//                System.out.println("Speed: " + player.getAttributeValue(ATTACK_SPEED));
//            }

            mapBaseStats.forEach((k, v) -> mapPotentials.merge(k, v, Integer::sum));

            if (mapPotentials.get("SPEED") > 0) {
                player.addEffect(new MobEffectInstance(
                        EffectsInit.EQUIP_SPEED_BOOST.get(),
                        5, // duration in tick
                        mapPotentials.get("SPEED") - 1,
                        false, false));
            }

            if (mapPotentials.get("MAX HP") > 0) {
                player.addEffect(new MobEffectInstance(
                        EffectsInit.EQUIP_HEALTH_BOOST.get(),
                        5, // duration in tick
                        mapPotentials.get("MAX HP") - 1,
                        false, false));
            }

            if (mapPotentials.get("STATS") > 0) {
                player.addEffect(new MobEffectInstance(
                        EffectsInit.EQUIP_STATS_PERCENT_BOOST.get(),
                        5, // duration in tick
                        mapPotentials.get("STATS"),
                        false, false));
            }

            if (mainHandItem.getItem() instanceof IBaseEquip) {
                if (mapPotentials.get("ATTACK") > 0) {
                    player.addEffect(new MobEffectInstance(
                            EffectsInit.EQUIP_ATTACK_BOOST.get(),
                            5, // duration in tick
                            mapPotentials.get("ATTACK") - 1,
                            false, false));
                }

                if (mapPotentials.get("ATT") > 0) {
                    player.addEffect(new MobEffectInstance(
                            EffectsInit.EQUIP_ATTACK_PERCENT_BOOST.get(),
                            5, // duration in tick
                            mapPotentials.get("ATT") - 1,
                            false, false));
                }

                Variables.set(player, "mAttackBoost",
                        mapPotentials.get("M.ATTACK") * (1 + mapPotentials.get("M.ATT") * 0.01));
            }

            if (mapPotentials.get("JUMP") > 0) {
                player.addEffect(new MobEffectInstance(
                        EffectsInit.JUMP_PERCENT_BOOST.get(),
                        5, // duration in tick
                        mapPotentials.get("JUMP"),
                        false, false));
            } else {
                JumpPercentBoostMobEffect.equipValue = 0;
            }

            if (mapPotentials.get("DEF") > 0) {
                player.addEffect(new MobEffectInstance(
                        EffectsInit.DEFENSE_PERCENT_BOOST.get(),
                        5, // duration in tick
                        mapPotentials.get("DEF"),
                        false, false));
            } else {
                DefensePercentBoostMobEffect.equipValue = 0;
            }

            if (!player.hasEffect(EffectsInit.BUFF_HASTE.get())) {
                JumpPercentBoostMobEffect.buffValue = 0;
            }

            if (!player.hasEffect(EffectsInit.BUFF_IRON_WILL.get())) {
                DefensePercentBoostMobEffect.buffValueP = 0;
            }

            if (!player.hasEffect(EffectsInit.BUFF_RAGE.get())) {
                DefensePercentBoostMobEffect.buffValueN = 0;
            }
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
            for (int i = 0; i < VARIABLE_COUNT; i++) {
                if (!PlayerVariables.isLifeTime.get(i))
                    clone.values.set(i, original.values.get(i));
            }

            // player lifetime variables
            if (!event.isWasDeath()) {
                for (int i = 0; i < VARIABLE_COUNT; i++) {
                    if (PlayerVariables.isLifeTime.get(i))
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
        public static final int VARIABLE_COUNT = 12;
        public List<Object> values = Arrays.asList(new Object[] {
                MapleCraftConstants.MAX_PLAYER_MANA_POINTS,
                0.0D,
                0,
                0, 0, 0, 0,
                0.0F, 0.0F, 0.0F, 0.0F,
                DEFAULT_STATE
        });

        public static List<String> names = List.of(
                "playerManaPoints",
                "mAttackBoost",
                "jobType",
                "skillId0", "skillId1", "skillId2", "skillId3",
                "skillCd0", "skillCd1", "skillCd2", "skillCd3",
                "questState");

        public static List<String> types = List.of(
                "double",
                "double",
                "int",
                "int", "int", "int", "int",
                "float", "float", "float", "float",
                "string");

        public static List<Boolean> isLifeTime = List.of(
                true,
                true,
                false,
                false, false, false, false,
                false, false, false, false,
                false);

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
            c.values.set(PlayerVariables.names.indexOf(variableName), value);
            c.syncPlayerVariables(entity);
        });
    }

    public static Object get(LivingEntity entity, String variableName) {
        PlayerVariables v = (entity.getCapability(Variables.PLAYER_VARIABLES_CAPABILITY, null)
                .orElse(new Variables.PlayerVariables()));
        return v.values.get(PlayerVariables.names.indexOf(variableName));
    }
}
