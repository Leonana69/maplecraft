package net.maplecraft.utils;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Objects;

public class MapleProjectileEntity extends AbstractArrow {
    public LivingEntity target = null;
    public int skillID = 0;
    public float power = 0;
    public float accuracy = 0;

    public MapleProjectileEntity(EntityType<? extends MapleProjectileEntity> type, Level world) {
        super(type, world);
    }

    public MapleProjectileEntity(EntityType<? extends MapleProjectileEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    @Override // generate particle effect while flying
    public void tick() {
        super.tick();
        if (this.level instanceof ServerLevel _level && !this.inGround) {
            _level.sendParticles(ParticleTypes.CRIT,
                    this.getX(), this.getY(), this.getZ(),
                    1, 0.1, 0.1, 0.1, 0.0);
        }

        if (!this.inGround && target != null && !target.isDeadOrDying()) {
            Vec3 move = new Vec3(this.target.getX() - this.getX(),
                    this.target.getY() + this.target.getBbHeight() / 2 - this.getY(),
                    this.target.getZ() - this.getZ());

            move = move.normalize().scale(power);
            this.setDeltaMovement(move);
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

    public String getProjectileName() {
        return "";
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        this.target = null;
        this.getLevel().playSound(null, result.getEntity().getX(), result.getEntity().getY(), result.getEntity().getZ(),
                Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_mob_damage"))),
                SoundSource.PLAYERS, 1, 1);
        result.getEntity().invulnerableTime = 0;

        if (AllSkillList.SKILLS.get(skillID) != null
                && this.getOwner() instanceof Player player
                && result.getEntity() instanceof LivingEntity livingEntity) {
            SkillItem skill = (SkillItem) AllSkillList.SKILLS.get(skillID).asItem();
            skill.onHitEffect(player, livingEntity);
        }
    }
}
