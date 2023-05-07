package com.maplecraft.entity.mobs;

import com.maplecraft.MapleCraftMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MapleMobEntityModel<T extends MapleMobEntity> extends AnimatedGeoModel<T> {
    @Override
    public ResourceLocation getModelResource(T object) {
        return new ResourceLocation(MapleCraftMod.MODID, "geo/" + object.entityName + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return new ResourceLocation(MapleCraftMod.MODID, "textures/entities/" + object.entityName + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T object) {
        return new ResourceLocation(MapleCraftMod.MODID, "animations/" + object.entityName + ".animation.json");
    }
}
