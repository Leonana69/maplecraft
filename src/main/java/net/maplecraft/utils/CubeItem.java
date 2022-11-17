package net.maplecraft.utils;

import io.netty.buffer.Unpooled;
import net.maplecraft.init.TabsInit;
import net.maplecraft.world.inventory.CubeGUIMenu;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import java.util.Map;

import static java.lang.Math.abs;
import static net.maplecraft.utils.PotentialType.getRandomPotential;

public class CubeItem extends Item {
    public final CubeType cubeType;

    public CubeItem(CubeType cubeType) {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT));
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

    public boolean execute(Player player, Map slots, IBaseEquip baseEquip) {
        int cur = baseEquip.getPotentialRarity().type;
        if (cur <= this.cubeType.highest.type && cur > 0) {
            if (cur < 4 && abs(player.getRandom().nextFloat()) < this.cubeType.chance[cur - 1]) {
                // level up potential
                cur += 1;
            }

            PotentialType [] pt = new PotentialType[] {
                    getRandomPotential(baseEquip.getCategory(), cur),
                    getRandomPotential(baseEquip.getCategory(), cur),
                    getRandomPotential(baseEquip.getCategory(), cur),
            };

//            ((Slot) slots.get(0)).getItem().getCapability();

            baseEquip.setPotential(PotentialRarity.get(cur), pt);
            return true;
        }
        return false;
    }
}
