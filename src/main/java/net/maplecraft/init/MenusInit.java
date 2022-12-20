package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.inventory.CubeMenu;
import net.maplecraft.inventory.QuestMenu;
import net.maplecraft.inventory.SkillMenu;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

public class MenusInit {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MapleCraftMod.MODID);
    public static final RegistryObject<MenuType<CubeMenu>> CUBE_MENU = REGISTRY.register("cube_menu", () -> new MenuType<>(CubeMenu::getClientMenu));
    public static final RegistryObject<MenuType<SkillMenu>> SKILL_MENU = REGISTRY.register("skill_menu", () -> new MenuType<>(SkillMenu::getClientMenu));
    public static final RegistryObject<MenuType<QuestMenu>> QUEST_MENU = REGISTRY.register("quest_menu", () -> new MenuType<>(QuestMenu::getClientMenu));
}
