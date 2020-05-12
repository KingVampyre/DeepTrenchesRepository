package github.kingvampire.DeepTrenches.api.capabilities.tame;

import static net.minecraft.world.GameRules.SHOW_DEATH_MESSAGES;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface ITame {

    /**
     * Override EntityLiving::canAttack
     */
    default boolean canAttack(LivingEntity target) {
	return !this.isOwner(target);
    }

    CreatureEntity getCreatureEntity();

    @Nullable
    PlayerEntity getOwner();

    @Nullable
    UUID getOwnerId();

    /**
     * Override EntityLiving::getTeam
     */
    default Team getTeam() {

	if (this.isTamed()) {
	    LivingEntity livingentity = this.getOwner();

	    if (livingentity != null)
		return livingentity.getTeam();
	}

	return null;
    }

    /**
     * Override EntityLiving::handleStatusUpdate
     */
    @OnlyIn(Dist.CLIENT)
    default void handleStatusUpdate(byte id) {

	if (id == 7)
	    this.playTameEffect(ParticleTypes.HEART);
	else if (id == 6)
	    this.playTameEffect(ParticleTypes.SMOKE);
    }

    /**
     * Override EntityLiving::isOnSameTeam
     */
    default boolean isOnSameTeam(Entity entityIn) {

	if (this.isTamed()) {
	    LivingEntity livingentity = this.getOwner();

	    if (entityIn == livingentity)
		return true;

	    if (livingentity != null)
		return livingentity.isOnSameTeam(entityIn);

	}

	return false;
    }

    default boolean isOwner(LivingEntity entityIn) {
	return entityIn == this.getOwner();
    }

    boolean isSitting();;

    boolean isTamed();

    /**
     * Override EntityLiving::onDeath
     */
    default void onDeath(DamageSource cause) {
	LivingEntity livingEntity = this.getCreatureEntity();
	World world = livingEntity.getEntityWorld();

	LivingEntity owner = this.getOwner();
	GameRules gameRules = world.getGameRules();

	if (owner == null)
	    return;

	ITextComponent textComponent = livingEntity.getCombatTracker().getDeathMessage();

	if (!world.isRemote() && gameRules.getBoolean(SHOW_DEATH_MESSAGES))
	    owner.sendMessage(textComponent);
    }

    default void playTameEffect(IParticleData iparticledata) {
	LivingEntity livingEntity = this.getCreatureEntity();

	Random random = livingEntity.getRNG();
	World world = livingEntity.getEntityWorld();

	for (int i = 0; i < 7; ++i) {
	    double xSpeed = random.nextGaussian() * 0.02F;
	    double ySpeed = random.nextGaussian() * 0.02F;
	    double zSpeed = random.nextGaussian() * 0.02F;

	    float width = livingEntity.getWidth();

	    double x = livingEntity.posX + (double) (random.nextFloat() * width * 2F) - (double) width;
	    double y = livingEntity.posY + 0.5 + (double) (random.nextFloat() * width);
	    double z = livingEntity.posZ + (double) (random.nextFloat() * width * 2F) - (double) width;

	    world.addParticle(iparticledata, x, y, z, xSpeed, ySpeed, zSpeed);
	}

    }

    void setOwnerId(@Nullable UUID ownerId);

    void setSitting(boolean isSitting);

    void setTamed(boolean isTamed);

    default void setTamedBy(PlayerEntity player) {
	this.setOwnerId(player.getUniqueID());
	this.setTamed(true);

	if (player instanceof ServerPlayerEntity) {
	    // TODO CriteriaTriggers
	}

    }

    default boolean shouldAttackEntity(LivingEntity target, LivingEntity owner) {

	if (this.isTamed() && this.getOwner() == owner)
	    return false;

	if (target instanceof PlayerEntity && owner instanceof PlayerEntity)
	    return !PlayerEntity.class.cast(owner).canAttackPlayer(PlayerEntity.class.cast(target));

	return false;
    }

}
