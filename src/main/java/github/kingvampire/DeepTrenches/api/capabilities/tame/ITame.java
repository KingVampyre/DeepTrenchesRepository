package github.kingvampire.DeepTrenches.api.capabilities.tame;

import static github.kingvampire.DeepTrenches.api.capabilities.age.AgeProvider.AGE_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.breed.BreedProvider.BREED_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static net.minecraft.util.Hand.MAIN_HAND;
import static net.minecraft.world.GameRules.SHOW_DEATH_MESSAGES;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import github.kingvampire.DeepTrenches.api.capabilities.age.IAge;
import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.capabilities.breed.IBreed;
import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.events.ModEventFactory;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;

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

    int getTameChance();

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

    boolean isSitting();

    boolean isTamed();

    boolean isTameItem(ItemStack stack);;

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

    /**
     * Uses the same logic as {@code WolfEntity::processInteract}.
     * 
     * Override EntityLiving::processInteract to use it
     */
    default boolean processInteract(PlayerEntity player, Hand hand) {
	CreatureEntity creature = this.getCreatureEntity();
	World world = creature.getEntityWorld();

	LazyOptional<IAge> age = creature.getCapability(AGE_CAPABILITY);
	LazyOptional<IAnger> anger = creature.getCapability(ANGER_CAPABILITY);
	LazyOptional<IGroup> group = creature.getCapability(GROUP_CAPABILITY);
	LazyOptional<IBreed> breed = creature.getCapability(BREED_CAPABILITY);
	LazyOptional<ITame> tame = creature.getCapability(TAME_CAPABILITY);

	if (!world.isRemote() && hand == MAIN_HAND) {
	    ItemStack stack = player.getHeldItem(hand);
	    Food food = stack.getItem().getFood();

	    if (anger.isPresent() && age.isPresent() && group.isPresent() && breed.isPresent() && tame.isPresent()) {
		IAge iage = age.orElseThrow(IllegalArgumentException::new);
		IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);
		IBreed ibreed = breed.orElseThrow(IllegalArgumentException::new);
		IGroup igroup = group.orElseThrow(IllegalArgumentException::new);
		ITame itame = tame.orElseThrow(IllegalArgumentException::new);

		if (itame.isTamed()) {
		    float health = creature.getHealth();
		    float maxHealth = creature.getMaxHealth();

		    if (ibreed.isBreedingItem(stack) && health != maxHealth) {
			float healing = food.getHealing();

			if (!player.abilities.isCreativeMode)
			    stack.shrink(1);

			creature.heal(healing);

			return true;
		    }

		    if (itame.isOwner(player) && stack.isEmpty()) {
			creature.getNavigator().clearPath();
			creature.setAttackTarget(null);

			itame.setSitting(!itame.isSitting());
		    }

		} else if (this.isTameItem(stack) && !ianger.isAngry()) {
		    Random random = creature.getRNG();
		    int chance = this.getTameChance();

		    if (!player.abilities.isCreativeMode)
			stack.shrink(1);

		    if (random.nextInt(chance) == 0 && !ModEventFactory.onTame(creature, player)) {
			igroup.setGroupLeader(null);
			igroup.setGroup(Sets.newHashSet());

			creature.setAttackTarget(null);

			itame.setTamedBy(player);
			itame.setSitting(false);

			creature.getNavigator().clearPath();
			world.setEntityState(creature, (byte) 7);
		    } else
			world.setEntityState(creature, (byte) 6);

		    return true;
		}

		if (iage.processInteract(player, hand))
		    return true;

		if (itame.isTamed() && ibreed.processInteract(iage, player, hand))
		    return true;

	    }
	}

	return false;
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
