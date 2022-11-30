package net.maplecraft.entities.boss.zakum;

import net.maplecraft.MapleCraftMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BossZakumHandEntityModel extends AnimatedGeoModel<BossZakumHandEntity> {
    @Override
    public ResourceLocation getModelResource(BossZakumHandEntity object) {
        return new ResourceLocation(MapleCraftMod.MODID, "geo/boss_zakum_hand_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BossZakumHandEntity object) {
        return new ResourceLocation(MapleCraftMod.MODID, "textures/entities/boss_zakum_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BossZakumHandEntity animatable) {
        return new ResourceLocation(MapleCraftMod.MODID, "animations/boss_zakum_hand_entity.animation.json");
    }
}
