package net.maplecraft.utils;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MapleArmorItem extends BaseEquipItem {
    protected final EquipmentSlot slot;
    private final float toughness;
    protected final float knockBackResistance;

    public MapleArmorItem(Properties itemProperties, EquipCategory ec, BonusStats bs, float toughness, float knockBackResistance) {
        super(itemProperties, ec, bs);
        switch (ec) {
            case HELMET -> slot = EquipmentSlot.HEAD;
            case CHEST_PLATE -> slot = EquipmentSlot.CHEST;
            case LEGGINGS -> slot = EquipmentSlot.LEGS;
            case BOOTS -> slot = EquipmentSlot.FEET;
            default -> slot = EquipmentSlot.MAINHAND;
        }

        this.toughness = toughness;
        this.knockBackResistance = knockBackResistance;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(itemStack);
        ItemStack itemStack1 = player.getItemBySlot(equipmentslot);
//        if (itemStack1.isEmpty()) {
//            player.setItemSlot(equipmentslot, itemStack.copy());
//            if (!world.isClientSide()) {
//                player.awardStat(Stats.ITEM_USED.get(this));
//            }
//
//            itemStack.setCount(0);
//            return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
//        } else {
//            return InteractionResultHolder.fail(itemStack);
//        }
        System.out.println("Slot: " + equipmentslot);

        return InteractionResultHolder.pass(itemStack);
    }

    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return this.slot;
    }
}
