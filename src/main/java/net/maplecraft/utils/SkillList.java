package net.maplecraft.utils;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.item.skill.SkillTeleport;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;

public class SkillList {
    public static Map<String, ItemLike> SKILLS  = new HashMap<String, ItemLike>() {{
        put(SkillTeleport.itemName, ItemsInit.SKILL_TELEPORT.get());
    }};
}
