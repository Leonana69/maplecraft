package net.maplecraft.utils;

import net.maplecraft.init.TabsInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SkillItem extends Item {
    public String itemName;
    public double manaCost;
    public SkillBaseData skillBaseData;

    public SkillItem(String itemName, SkillBaseData data) {
        super(new Properties().tab(TabsInit.TAB_MAPLE_CRAFT).stacksTo(1));
        this.itemName = itemName;
        skillBaseData = data;
        manaCost = data.manaCostBase;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemStack, world, list, flag);

        list.add(Component.literal(
                Component.translatable("utils.maplecraft.skill_job_require").getString()
                        + skillBaseData.jobReq.typeName
        ));

        list.add(Component.translatable("utils.maplecraft.base_equip_item_divider"));

        list.add(Component.literal(
                Component.translatable("utils.maplecraft.skill_level").getString()
                        + skillBaseData.level + "], "
                        + Component.translatable("utils.maplecraft.skill_mana_cost").getString()
                        + manaCost));

        String s = "item.maplecraft." + itemName + "_description";
        list.add(Component.translatable(s));
    }
}
