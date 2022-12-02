package net.maplecraft.utils;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.maplecraft.init.TabsInit;
import net.maplecraft.item.etc.EtcAdvancedMonsterCrystalItem;
import net.maplecraft.item.etc.EtcBasicMonsterCrystalItem;
import net.maplecraft.item.etc.EtcIntermediateMonsterCrystalItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class WeaponItem extends Item implements IBaseEquip {
    public EquipBaseData baseEquipData;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    @SubscribeEvent
    public static void attackEntityEvent(AttackEntityEvent event){
        ItemStack itemStack = event.getEntity().getMainHandItem();
        if (itemStack.getItem() instanceof WeaponClawItem
                || itemStack.getItem() instanceof WeaponBowItem)
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
    public EquipBaseData getBaseEquipData() {
        return baseEquipData;
    }

    @Override
    public EquipCategory getCategory() {
        return baseEquipData.category;
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return false;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public boolean isValidRepairItem(ItemStack weapon, ItemStack ingredient) {
        switch (baseEquipData.levelReq) {
            case 0, 5 -> {
                return ingredient.getItem() instanceof EtcBasicMonsterCrystalItem;
            }
            case 10 -> {
                return ingredient.getItem() instanceof EtcIntermediateMonsterCrystalItem;
            }
            case 15 -> {
                return ingredient.getItem() instanceof EtcAdvancedMonsterCrystalItem;
            }
        }
        return false;
    }
}
