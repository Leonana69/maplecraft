package com.maplecraft.entity;

import com.maplecraft.MapleCraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MapleMobEntityModel<T extends LivingEntity & IAnimatable & MapleLivingEntity> extends AnimatedGeoModel<T> {
    @Override
    public ResourceLocation getModelResource(T object) {
        return new ResourceLocation(MapleCraftMod.MODID, "geo/" + object.getEntityName() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return new ResourceLocation(MapleCraftMod.MODID, "textures/entities/" + object.getEntityName() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T object) {
        return new ResourceLocation(MapleCraftMod.MODID, "animations/" + object.getEntityName() + ".animation.json");
    }
}
