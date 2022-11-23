package net.maplecraft.client.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.maplecraft.network.Variables;
import net.maplecraft.utils.AllSkillList;
import net.maplecraft.utils.SkillItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class SkillSlotOverlay {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void eventHandler(RenderGuiEvent.Pre event) {
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
                int skillID = (int) Variables.get(player, "skillID" + i);
                ItemLike item = AllSkillList.SKILLS.get(skillID);
                if (skillID > 0 && item != null) {
                    SkillItem skill = (SkillItem) item.asItem();
                    RenderSystem.setShaderTexture(0, new ResourceLocation("maplecraft:textures/items/skills/" + skill.itemName + ".png"));
                    GuiComponent.blit(event.getPoseStack(), posX + 3 + i * 20, posY + 3, 0, 0, 16, 16, 16, 16);
                }
            }
        }

        RenderSystem.disableBlend();
    }
}
