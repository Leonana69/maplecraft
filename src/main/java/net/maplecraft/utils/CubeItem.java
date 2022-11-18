package net.maplecraft.utils;

import io.netty.buffer.Unpooled;
import net.maplecraft.init.TabsInit;
import net.maplecraft.world.customGUI.CubeGUIMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import java.util.Map;

import static java.lang.Math.abs;
import static net.maplecraft.utils.PotentialType.getRandomPotentialType;

public class CubeItem extends Item {
    public final CubeType cubeType;

    public CubeItem(Properties properties, CubeType cubeType) {
        super(properties.tab(TabsInit.TAB_MAPLE_CRAFT));
        this.cubeType = cubeType;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            BlockPos blockPos = new BlockPos(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ());
            NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return Component.literal("CubeGUI");
                }

                @Override
                public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                    return new CubeGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(blockPos));
                }
            }, blockPos);
        }

        return super.use(world, player, hand);
    }

    public void execute(Player player, Map slots, IBaseEquip baseEquip) {
        ItemStack itemStack = ((Slot) slots.get(0)).getItem();
        int cur = baseEquip.getPotentialRarity(itemStack).type;

        if (cur == 0) {
            player.displayClientMessage(Component.translatable("utils.maplecraft.cube_no_potential"), (false));
        } else if (cur > this.cubeType.highest.type) {
            player.displayClientMessage(Component.literal(
                    Component.translatable("utils.maplecraft.cube_quality_mismatch").getString() +
                            TextFormatter.format(cubeType.highest.typeName, cubeType.highest.color)
                    ),
                    (false));
        } else {
            float r = abs(player.getRandom().nextFloat());
            if (cur < 4 && r < this.cubeType.chance[cur - 1]) {
                // level up potential
                cur += 1;
            }

            PotentialType [] pt = new PotentialType[] {
                    getRandomPotentialType(baseEquip.getCategory(), cur),
                    getRandomPotentialType(baseEquip.getCategory(), cur),
                    getRandomPotentialType(baseEquip.getCategory(), cur),
            };


            player.displayClientMessage(Component.literal(
                    TextFormatter.format(
                            Component.translatable("utils.maplecraft.cube_set_potential").getString(),
                            PotentialRarity.get(cur).color)),
                    (false));
            // use one cube
            baseEquip.setPotential(itemStack, PotentialRarity.get(cur), pt);
            ((Slot) slots.get(1)).getItem().shrink(1);
        }
    }
}
