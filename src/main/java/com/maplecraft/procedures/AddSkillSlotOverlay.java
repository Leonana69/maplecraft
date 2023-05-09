package com.maplecraft.procedures;

import com.maplecraft.network.Variables;
import com.mojang.blaze3d.systems.RenderSystem;
import com.maplecraft.utils.AllSkillList;
import com.maplecraft.item.SkillItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class AddSkillSlotOverlay {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new AddSkillSlotOverlay());
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void eventHandler(RenderGuiEvent.Pre event) {
        int w = event.getWindow().getGuiScaledWidth();
        int h = event.getWindow().getGuiScaledHeight();
        int posX = w / 2 + 9;
        int posY = h - 72;

        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, new ResourceLocation("maplecraft:textures/screens/skill_slots.png"));
        GuiComponent.blit(event.getPoseStack(), posX, posY, 0, 0, 82, 22, 82, 22);

        Player player = Minecraft.getInstance().player;
        if (player != null) {
            for (int i = 0; i < 4; i++) {
                int skillId = (int) Variables.get(player, "skillId" + i);
                float cooldown = (float) Variables.get(player, "skillCd" + i);
                ItemLike item = AllSkillList.SKILLS.get(skillId);
                if (skillId > 0 && item != null) {
                    SkillItem skill = (SkillItem) item.asItem();
                    float f = skill.skillBaseData.cooldown;
                    RenderSystem.setShaderTexture(0, new ResourceLocation("maplecraft:textures/items/skills/" + skill.itemName + ".png"));
                    GuiComponent.blit(event.getPoseStack(), posX + 3 + i * 20, posY + 3, 0, 0, 16, 16, 16, 16);
                    if (cooldown > 0) {
                        RenderSystem.setShaderTexture(0, new ResourceLocation("maplecraft:textures/screens/skill_slot_cooldown.png"));
                        int percent = (int) (cooldown / skill.skillBaseData.cooldown * 16);
                        GuiComponent.blit(event.getPoseStack(), posX + 3 + i * 20, posY + 3 + (16 - percent), 0, 0, 16, percent, 16, 16);
                    }
                }
            }
        }

        RenderSystem.disableBlend();
    }
}
