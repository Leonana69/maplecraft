package net.maplecraft.entity.summon.holyDragon;

import net.maplecraft.MapleCraftMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HolyDragonEntityModel extends AnimatedGeoModel<HolyDragonEntity> {
    @Override
    public ResourceLocation getModelResource(HolyDragonEntity object) {
        return new ResourceLocation(MapleCraftMod.MODID, "geo/holy_dragon_entity.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HolyDragonEntity object) {
        return new ResourceLocation(MapleCraftMod.MODID, "textures/entities/holy_dragon_entity.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HolyDragonEntity animatable) {
        return new ResourceLocation(MapleCraftMod.MODID, "animations/holy_dragon_entity.animation.json");
    }
}
