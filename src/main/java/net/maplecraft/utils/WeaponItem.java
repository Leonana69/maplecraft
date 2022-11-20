package net.maplecraft.utils;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.maplecraft.init.TabsInit;
import net.maplecraft.network.EquipCapabilitiesProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mod.EventBusSubscriber
public class WeaponItem extends Item implements IBaseEquip {
    public EquipBaseData baseEquipData;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    @SubscribeEvent
    public static void attackEntityEvent(AttackEntityEvent event){
        ItemStack itemStack = event.getEntity().getMainHandItem();
        if (itemStack.getItem() instanceof WeaponClawItem || itemStack.getItem() instanceof WeaponBowItem)
            event.setCanceled(true);
    }

    public WeaponItem(EquipBaseData data) {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                .durability(data.levelReq * EquipBaseData.durationPerLevel + EquipBaseData.durationBase));
        this.baseEquipData = data;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        double as = data.baseStats.get("ATTACK_SPEED");
        as = 1 / (0.25 + as * 0.175) - 4.0;
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", as, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemStack, world, list, flag);
        appendHoverText(itemStack, list, baseEquipData);
    }

    @Override
    public boolean hasPotential(ItemStack itemstack) {
        return getEquipWiseData(itemstack).equipRarity != MapleRarity.COMMON;
    }

    @Override
    public List<Component> getTooltip(ItemStack itemstack) {
        return EquipWiseData.componentFromString(getEquipWiseData(itemstack).tooltip);
    }

    @Override
    public MapleRarity getPotentialRarity(ItemStack itemstack) {
        return getEquipWiseData(itemstack).equipRarity;
    }

    @Override
    public EquipBaseData getBaseEquipData() {
        return baseEquipData;
    }

    @Override
    public void setPotential(ItemStack itemstack, MapleRarity rarity, PotentialStats[] potentialStats) {
        getEquipWiseData(itemstack).equipRarity = rarity;
        getEquipWiseData(itemstack).potentials = potentialStats;
    }

    @Override
    public void setStarForce(ItemStack itemstack, int starForce) {
        getEquipWiseData(itemstack).starForce = starForce;
    }

    @Override
    public EquipCategory getCategory() {
        return baseEquipData.category;
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new EquipCapabilitiesProvider();
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return false;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

//    public String getSwingSound() {
//        return null;
//    };
//
//    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
//        boolean retVal = super.onEntitySwing(itemstack, entity);
//        if (entity.swingTime < 1 && getSwingSound() != null && entity instanceof Player)
//            entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
//                Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(getSwingSound()))),
//                SoundSource.PLAYERS, 1, 1);
//        return retVal;
//    }
}
