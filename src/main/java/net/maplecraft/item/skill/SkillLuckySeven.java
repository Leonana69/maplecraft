package net.maplecraft.item.skill;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.utils.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Objects;

import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;

public class SkillLuckySeven extends SkillItem {
    public static String itemName = "skill_lucky_seven";
    public static int skillID = 4001344;
    public static int cnt = 0;
    public SkillLuckySeven() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .jobReq(JobCategory.THIEF)
                        .weaponReq(EquipCategory.CLAW)
                        .damage(180)
                        .manaCost(2),
                new SkillHitEffectInstance()
                        .skillName(itemName)
                        .animeCount(4)
                        .textureSize(50, 51));
        this.consumeProjectile = true;
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getClosestEntity(player, 3, 10);
            if (!target.isEmpty()) {
//                scheduleDamage(player, target);
                ItemStack ammoStack = WeaponClawItem.findAmmo(player);
                Level world = player.level;

                if (!ammoStack.isEmpty() || player.getAbilities().instabuild) {
                    if (ammoStack.isEmpty()) {
                        ammoStack = new ItemStack(ItemsInit.UES_SUBI_THROWING_STARS.get());
                    }

                    MapleProjectileItem ammoItem = (MapleProjectileItem) ammoStack.getItem();
                    AbstractArrow ammoEntity = ammoItem.createArrow(world, player);

                    ((MapleProjectileEntity) ammoEntity).target = target.get(0);

                    ammoEntity.shoot(player.getViewVector(1).x, player.getViewVector(1).y, player.getViewVector(1).z, WeaponClawItem.power, WeaponClawItem.accuracy);

                    double damage = (player.getAttributeValue(ATTACK_DAMAGE) + ammoItem.bonusDamage) / WeaponClawItem.power;

                    ammoEntity.setBaseDamage(damage);
                    world.addFreshEntity(ammoEntity);

                    if (!player.getAbilities().instabuild) {
                        ammoStack.shrink(1);
                        if (ammoStack.isEmpty()) {
                            player.getInventory().removeItem(ammoStack);
                        }
                    }

                    world.playSound(null, player.getX(), player.getY(), player.getZ(),
                            Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_claw_attack"))),
                            SoundSource.PLAYERS, 1, 1);
                }
            }
        }
    }
}
