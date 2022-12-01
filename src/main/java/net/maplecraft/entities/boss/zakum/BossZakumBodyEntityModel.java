package net.maplecraft.entities.boss.zakum;

import net.maplecraft.MapleCraftMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BossZakumBodyEntityModel extends AnimatedGeoModel<BossZakumBodyEntity> {
    @Override
    public ResourceLocation getModelResource(BossZakumBodyEntity object) {
        return new ResourceLocation(MapleCraftMod.MODID, "geo/boss_zakum_body_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BossZakumBodyEntity object) {
        return new ResourceLocation(MapleCraftMod.MODID, "textures/entities/boss_zakum_body_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BossZakumBodyEntity animatable) {
        return new ResourceLocation(MapleCraftMod.MODID, "animations/boss_zakum_body_entity.animation.json");
    }
}
