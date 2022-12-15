package net.maplecraft.effect;

import net.maplecraft.utils.MapleMobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class BuffShadowPartnerMobEffect extends MapleMobEffect {
    public BuffShadowPartnerMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x111111);
    }

    @Override
    public String getDescriptionId() {
        return "item.maplecraft.skill_shadow_partner";
    }
}
