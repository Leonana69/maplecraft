package net.maplecraft.utils;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;
public class MapleArmorItem extends BaseEquipItem {
    private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    protected final EquipmentSlot slot;
    private final float toughness;
    protected final float knockBackResistance;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

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

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[this.slot.getIndex()];
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", (double)this.baseStats.get("DEFENSE"), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", (double)this.toughness, AttributeModifier.Operation.ADDITION));
        if (this.knockBackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", (double)this.knockBackResistance, AttributeModifier.Operation.ADDITION));
        }
        this.defaultModifiers = builder.build();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(itemStack);
        ItemStack itemStack1 = player.getItemBySlot(equipmentslot);

        if (itemStack1.isEmpty()) {
            player.setItemSlot(equipmentslot, itemStack.copy());
//            this.applyStats(player);
            if (!world.isClientSide()) {
                player.awardStat(Stats.ITEM_USED.get(this));
            }

            itemStack.setCount(0);
            return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
        } else {
            return InteractionResultHolder.fail(itemStack);
        }
    }

    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return this.slot;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }
//
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == this.slot ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

//    public void applyStats(Player player) {
//        UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[this.slot.getIndex()];
//        AttributeModifier m = new AttributeModifier(uuid, "Armor modifier", (double)this.baseStats.get("DEFENSE"), AttributeModifier.Operation.ADDITION);
//        player.getAttribute(Attributes.ARMOR).removeModifier(m);
//        player.getAttribute(Attributes.ARMOR).addTransientModifier(m);
//    }
//
//    public void removeStats(Player player) {
//        UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[this.slot.getIndex()];
//        AttributeModifier m = new AttributeModifier(uuid, "Armor modifier", (double)this.baseStats.get("DEFENSE"), AttributeModifier.Operation.ADDITION);
//        player.getAttribute(Attributes.ARMOR).removeModifier(m);
//    }
}
