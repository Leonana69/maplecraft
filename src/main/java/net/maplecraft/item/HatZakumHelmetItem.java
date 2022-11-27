package net.maplecraft.item;

import net.maplecraft.client.model.ZakumHelmetEntityModel;
import net.maplecraft.init.ItemsInit;
import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.EquipCategory;
import net.maplecraft.utils.MapleArmorItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

public class HatZakumHelmetItem extends MapleArmorItem {
    public static String itemName = "hat_zakum_helmet";
    public HatZakumHelmetItem() {
        super(itemName,
                500,
                new EquipBaseData().category(EquipCategory.HAT)
                        .levelReq(15)
                        .addStat("ARMOR", 10)
                        .addStat("STATS", 5)
                        .addStat("MAX HP", 5)
                        .addStat("SPEED", 5)
                        .addStat("JUMP", 5),
                () -> Ingredient.of(ItemsInit.ETC_MESO_LARGE.get()));
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
                        Map.of("head",
                                new ZakumHelmetEntityModel<>(
                                        Minecraft.getInstance().getEntityModels().bakeLayer(ZakumHelmetEntityModel.LAYER_LOCATION)).Head,
                                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg",
                                new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
                armorModel.crouching = living.isShiftKeyDown();
                armorModel.riding = defaultModel.riding;
                armorModel.young = living.isBaby();
                return armorModel;
            }
        });
    }
}
