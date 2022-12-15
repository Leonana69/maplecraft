package net.maplecraft.effect.buffEffect;

import net.maplecraft.effect.MapleMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class BuffSoulArrowMobEffect extends MapleMobEffect {
    public BuffSoulArrowMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xfee169);
    }

    @Override
    public String getDescriptionId() {
        return "item.maplecraft.skill_soul_arrow";
    }
}
