package net.maplecraft.init;

import net.maplecraft.MapleCraftMod;
import net.maplecraft.world.inventory.CubeGUIMenu;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

public class GUIMenuInit {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MapleCraftMod.MODID);
    public static final RegistryObject<MenuType<CubeGUIMenu>> CUBE_GUI_MENU = REGISTRY.register("cube_gui_menu", () -> IForgeMenuType.create(CubeGUIMenu::new));
}
