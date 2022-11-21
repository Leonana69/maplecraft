package net.maplecraft.utils;

import net.maplecraft.init.TabsInit;
import net.maplecraft.network.Variables;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;

public class SkillItem extends Item {
    public String itemName;
    public SkillBaseData skillBaseData;

    public SkillItem(String itemName, SkillBaseData data) {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT).stacksTo(1));
        this.itemName = itemName;
        skillBaseData = data;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemStack, world, list, flag);

        list.add(Component.literal(
                Component.translatable("utils.maplecraft.skill_job_require").getString()
                        + skillBaseData.jobReq.typeName
        ));

        list.add(Component.translatable("utils.maplecraft.base_equip_item_divider"));

        String s = Component.translatable("utils.maplecraft.skill_level").getString()
                + skillBaseData.level + "], "
                + Component.translatable("utils.maplecraft.skill_mana_cost").getString()
                + skillBaseData.manaCost;
        if (skillBaseData.healthCost > 0) {
            s += Component.translatable("utils.maplecraft.skill_health_cost").getString()
                    + skillBaseData.healthCost;
        }
        list.add(Component.literal(s));

        s = "item.maplecraft." + itemName + "_description";
        list.add(Component.translatable(s));
    }

    public void skillEffect(Player player) {}

    public boolean canUse(Player player) {
        double mana = (double) Variables.get(player, "playerManaPoints");
        JobCategory job = JobCategory.getJob((int) Variables.get(player, "jobType"));

        ItemStack mainHandItemStack = player.getMainHandItem();
        EquipCategory weapon = EquipCategory.NONE;
        if (mainHandItemStack.getItem() instanceof IBaseEquip equip) {
            weapon = equip.getCategory();
        }

        return (this.skillBaseData.weaponReq.type == -1 || weapon == this.skillBaseData.weaponReq)
                && mana >= this.skillBaseData.manaCost
                && this.skillBaseData.jobReq.isSuccessor(job);
    }

    public void postEffect(Player player) {
        // cost mana
        if (!player.level.isClientSide && !player.getAbilities().instabuild) {
            double mana = (double) Variables.get(player, "playerManaPoints");
            Variables.set(player, "playerManaPoints", mana - this.skillBaseData.manaCost);
        }

        player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_" + itemName))),
                SoundSource.PLAYERS, 1, 1);
    }
}
