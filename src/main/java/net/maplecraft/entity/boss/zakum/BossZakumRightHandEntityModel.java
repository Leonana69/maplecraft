package net.maplecraft.entity.boss.zakum;

import net.maplecraft.MapleCraftMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BossZakumRightHandEntityModel extends AnimatedGeoModel<BossZakumRightHandEntity> {
    @Override
    public ResourceLocation getModelResource(BossZakumRightHandEntity object) {
        return new ResourceLocation(MapleCraftMod.MODID, "geo/boss_zakum_right_hand_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BossZakumRightHandEntity object) {
        return new ResourceLocation(MapleCraftMod.MODID, "textures/entities/boss_zakum_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BossZakumRightHandEntity animatable) {
        return new ResourceLocation(MapleCraftMod.MODID, "animations/boss_zakum_right_hand_entity.animation.json");
    }
}
