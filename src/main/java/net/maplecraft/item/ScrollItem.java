package net.maplecraft.item;

import io.netty.buffer.Unpooled;
import net.maplecraft.client.screens.CubeScreen;
import net.maplecraft.init.TabsInit;
import net.maplecraft.utils.*;
import net.maplecraft.inventory.CubeMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static net.maplecraft.utils.PotentialType.getRandomPotentialType;

public class ScrollItem extends MapleItem {
    public final ScrollType scrollType;

    public ScrollItem(MapleItemProperties itemProperties, ScrollType scrollType) {
        super(itemProperties.properties(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT)));
        this.scrollType = scrollType;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            BlockPos blockPos = new BlockPos(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ());
            CubeScreen.guiType = 2;
            NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return Component.literal("Cube Menu");
                }

                @Override
                public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                    return new CubeMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(blockPos));
                }
            }, blockPos);
        }

        return super.use(world, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        Component description = Component.translatable("item.maplecraft.use_potential_scroll_description", (int) (this.scrollType.chance * 100));
        list.add(description);
    }

    public void execute(Player player, ItemStack itemStack0, ItemStack itemStack1) {
        IBaseEquip baseEquip = (IBaseEquip) itemStack0.getItem();
        EquipBaseData equipBaseData = baseEquip.getBaseEquipData();
        if (!equipBaseData.canGetPotential) {
            player.displayClientMessage(Component.translatable("utils.maplecraft.scroll_item_can_not_get_potential"),
                    false);
            return;
        }

        int rarity = baseEquip.getPotentialRarity(itemStack0).type;
        CubeItem.updated = false;
        if (rarity > this.scrollType.highest.type) {
            player.displayClientMessage(Component.literal(
                            Component.translatable("utils.maplecraft.scroll_quality_mismatch").getString() +
                                    TextFormatter.format(scrollType.highest.typeName, scrollType.highest.color)
                    ),
                    false);
        } else {
            // use one scroll
            itemStack1.shrink(1);
            float r = abs(player.getRandom().nextFloat());
            if (r < this.scrollType.chance) {
                // set potential to scroll's level
                rarity = this.scrollType.highest.type;
                int secondaryRarity = Math.max(rarity - 1, 1);
                PotentialStats [] ps = new PotentialStats[] {
                        new PotentialStats(MapleRarity.get(rarity), getRandomPotentialType(equipBaseData.category, rarity)),
                        new PotentialStats(MapleRarity.get(secondaryRarity), getRandomPotentialType(equipBaseData.category, secondaryRarity)),
                        new PotentialStats(MapleRarity.get(secondaryRarity), getRandomPotentialType(equipBaseData.category, secondaryRarity)),
                };

                player.displayClientMessage(Component.literal(
                                TextFormatter.format(
                                        Component.translatable("utils.maplecraft.cube_set_potential").getString(),
                                        MapleRarity.get(rarity).color)),
                        false);

                player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_enchant_success"))),
                        SoundSource.PLAYERS, 1, 1);

                CubeItem.newRarity = MapleRarity.get(rarity);
                CubeItem.newPotentials = ps;
                CubeItem.updated = true;
            } else {
                player.displayClientMessage(Component.translatable("utils.maplecraft.cube_set_potential_failed"), false);
                player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_enchant_failed"))),
                        SoundSource.PLAYERS, 1, 1);
            }
        }
    }
}