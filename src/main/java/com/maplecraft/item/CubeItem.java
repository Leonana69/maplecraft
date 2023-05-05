package com.maplecraft.item;

import com.maplecraft.inventory.CubeMenu;
import com.maplecraft.utils.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static java.lang.Math.abs;

public class CubeItem extends MapleItem {
    public final CubeType cubeType;

    public CubeItem(MapleItemProperties itemProperties, CubeType cubeType) {
        super(itemProperties);
        this.cubeType = cubeType;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, CubeMenu.getServerMenu(0));
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
        return super.use(world, player, hand);
    }

    public void execute(Player player, ItemStack itemStack0, ItemStack itemStack1) {
        IBaseEquip baseEquip = (IBaseEquip) itemStack0.getItem();
        int rarity = baseEquip.getPotentialRarity(itemStack0).type;
        EquipBaseData equipBaseData = baseEquip.getBaseEquipData();

        CubeMenu cubeMenu = (CubeMenu) player.containerMenu;
        cubeMenu.updated = false;
        if (rarity == 0) {
            player.displayClientMessage(Component.translatable("utils.maplecraft.cube_no_potential"), false);
        } else if (rarity > this.cubeType.highest.type) {
            player.displayClientMessage(Component.literal(
                    Component.translatable("utils.maplecraft.cube_quality_mismatch").getString() +
                            TextFormatter.format(cubeType.highest.typeName, cubeType.highest.color)
                    ),
                    false);
        } else {
            float r = abs(player.getRandom().nextFloat());
            if (rarity < 4 && r < this.cubeType.chance[rarity - 1]) {
                // level up potential
                rarity += 1;
            }

            int secondaryRarity = Math.max(rarity - 1, 1);
            PotentialStats[] ps = new PotentialStats[] {
                    new PotentialStats(MapleRarity.get(rarity), PotentialType.getRandomPotentialType(equipBaseData.category, rarity)),
                    new PotentialStats(MapleRarity.get(secondaryRarity), PotentialType.getRandomPotentialType(equipBaseData.category, secondaryRarity)),
                    new PotentialStats(MapleRarity.get(secondaryRarity), PotentialType.getRandomPotentialType(equipBaseData.category, secondaryRarity)),
            };

            player.displayClientMessage(Component.literal(
                    TextFormatter.format(
                            Component.translatable("utils.maplecraft.cube_set_potential").getString(),
                            MapleRarity.get(rarity).color)),
                    false);

            player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_enchant_success"))),
                    SoundSource.PLAYERS, 1, 1);

            // use one cube
            itemStack1.shrink(1);

            baseEquip.setNewPotential(itemStack0, MapleRarity.get(rarity), ps);
            cubeMenu.updated = true;
        }
    }
}
