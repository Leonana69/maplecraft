package com.maplecraft.entity.boss.zakum;

import com.maplecraft.MapleCraftMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BossZakumLeftHandEntityModel extends AnimatedGeoModel<BossZakumLeftHandEntity> {
    @Override
    public ResourceLocation getModelResource(BossZakumLeftHandEntity object) {
        return new ResourceLocation(MapleCraftMod.MODID, "geo/boss_zakum_left_hand_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BossZakumLeftHandEntity object) {
        return new ResourceLocation(MapleCraftMod.MODID, "textures/entities/boss_zakum_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BossZakumLeftHandEntity animatable) {
        return new ResourceLocation(MapleCraftMod.MODID, "animations/boss_zakum_left_hand_entity.animation.json");
    }
}
