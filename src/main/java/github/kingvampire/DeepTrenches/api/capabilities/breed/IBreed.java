package github.kingvampire.DeepTrenches.api.capabilities.breed;

import static github.kingvampire.DeepTrenches.api.capabilities.breed.BreedProvider.BREED_CAPABILITY;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import github.kingvampire.DeepTrenches.api.capabilities.age.IAge;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;

public interface IBreed {

    /**
     * Override CreatureEntity::attackEntityFrom
     */
    default boolean shouldAttackEntityFrom(DamageSource source, float amount) {
	CreatureEntity creature = this.getCreatureEntity();

	if (creature.isInvulnerableTo(source))
	    return false;

	this.resetInLove();

	return true;
    }

    default boolean canBreed() {
	return this.getInLove() <= 0;
    }

    default boolean canMateWith(CreatureEntity creature) {
	LazyOptional<IBreed> lazyOptional = creature.getCapability(BREED_CAPABILITY);

	if (lazyOptional.isPresent()) {
	    IBreed mateable = lazyOptional.orElse(null);

	    if (creature == this)
		return false;
	    else if (creature.getClass() != this.getCreatureEntity().getClass())
		return false;
	    else
		return this.isInLove() && mateable.isInLove();
	}

	return false;
    }

    CreatureEntity getCreatureEntity();

    int getInLove();

    @Nullable
    default ServerPlayerEntity getLoveCause() {
	World world = this.getCreatureEntity().getEntityWorld();

	UUID uuid = this.getPlayerInLove();

	if (uuid == null)
	    return null;

	PlayerEntity player = world.getPlayerByUuid(uuid);

	return player instanceof ServerPlayerEntity ? (ServerPlayerEntity) player : null;
    }

    UUID getPlayerInLove();

    /**
     * Override CreatureEntity::handleStatusUpdate
     */
    @OnlyIn(Dist.CLIENT)
    default void handleStatusUpdate(byte id) {

	if (id != 18)
	    return;

	for (int i = 0; i < 7; ++i)
	    this.playHeartEffect();
    }

    boolean isBreedingItem(ItemStack stack);

    default boolean isInLove() {
	return this.getInLove() > 0;
    }

    /**
     * Override CreatureEntity::livingTick
     */
    default void livingTick(IAge age) {

	if (age.getGrowingAge() != 0)
	    this.resetInLove();

	int inLove = this.getInLove();

	if (inLove > 0) {
	    this.setInLove(--inLove);

	    if (inLove % 10 == 0)
		this.playHeartEffect();
	}
    }

    default void playHeartEffect() {
	CreatureEntity creature = this.getCreatureEntity();
	World world = creature.getEntityWorld();

	Random rand = creature.getRNG();

	double width = creature.getWidth();
	double height = creature.getHeight();

	double xSpeed = rand.nextGaussian() * 0.02F;
	double ySpeed = rand.nextGaussian() * 0.02F;
	double zSpeed = rand.nextGaussian() * 0.02F;

	double x = creature.posX + (double) (rand.nextFloat() * width * 2F) - width;
	double y = creature.posY + 0.5D + (double) (rand.nextFloat() * height);
	double z = creature.posZ + (double) (rand.nextFloat() * width * 2F) - width;

	world.addParticle(ParticleTypes.HEART, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    /**
     * Override CreatureEntity::processInteract
     */
    default boolean processInteract(IAge age, PlayerEntity player, Hand hand) {
	ItemStack stack = player.getHeldItem(hand);

	if (this.isBreedingItem(stack)) {
	    if (age.getGrowingAge() == 0 && this.canBreed()) {
		this.setInLove(player);

		if (!player.abilities.isCreativeMode)
		    stack.shrink(1);

		return true;
	    }

	    if (age.isChild()) {
		int growingAge = age.getGrowingAge();
		int growSeconds = (int) (-growingAge / 20F * 0.1F);

		age.ageUp(growSeconds, true);

		if (!player.abilities.isCreativeMode)
		    stack.shrink(1);

		return true;
	    }
	}

	return false;
    }

    default void resetInLove() {
	this.setInLove(0);
    }

    void setInLove(int inLove);

    default void setInLove(@Nullable PlayerEntity player) {
	CreatureEntity creature = this.getCreatureEntity();
	World world = creature.getEntityWorld();

	this.setInLove(600);

	if (player != null)
	    this.setPlayerInLove(player.getUniqueID());

	world.setEntityState(creature, (byte) 18);
    }

    void setPlayerInLove(UUID playerInLove);

    /**
     * Override CreatureEntity::updateAITasks
     */
    default void updateAITasks(IAge age) {

	if (age.getGrowingAge() != 0)
	    this.resetInLove();
    }

}
