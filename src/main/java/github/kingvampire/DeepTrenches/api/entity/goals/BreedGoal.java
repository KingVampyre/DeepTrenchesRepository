package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.age.AgeProvider.AGE_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.breed.BreedProvider.BREED_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModAttributes.MOVEMENT_SPEED_BOOST;
import static net.minecraft.entity.ai.goal.Goal.Flag.LOOK;
import static net.minecraft.entity.ai.goal.Goal.Flag.MOVE;
import static net.minecraft.stats.Stats.ANIMALS_BRED;
import static net.minecraft.world.GameRules.DO_MOB_LOOT;

import java.util.Comparator;
import java.util.EnumSet;

import javax.annotation.Nullable;

import github.kingvampire.DeepTrenches.api.capabilities.age.IAge;
import github.kingvampire.DeepTrenches.api.capabilities.breed.IBreed;
import github.kingvampire.DeepTrenches.api.events.ModEventFactory;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class BreedGoal extends Goal {

    protected final IAge iage;
    protected final IBreed ibreed;
    protected final CreatureEntity creature;
    protected final double distance;
    protected final double speed;

    protected CreatureEntity mate;
    protected int runDelay;

    public BreedGoal(CreatureEntity creature, double distance) {
	this.creature = creature;
	this.distance = distance;
	this.speed = creature.getAttribute(MOVEMENT_SPEED_BOOST).getValue();

	this.iage = creature.getCapability(AGE_CAPABILITY).orElseThrow(IllegalArgumentException::new);
	this.ibreed = creature.getCapability(BREED_CAPABILITY).orElseThrow(IllegalArgumentException::new);

	this.setMutexFlags(EnumSet.of(MOVE, LOOK));
    }

    protected int getAdultAge() {
	return 6000;
    }

    protected int getChildAge() {
	return -24000;
    }

    protected int getExperience() {
	return this.creature.getRNG().nextInt(7) + 1;
    }

    @Nullable
    private boolean getNearbyMate() {
	AxisAlignedBB aabb = this.creature.getBoundingBox().grow(this.distance);
	World world = this.creature.getEntityWorld();

	EntityPredicate predicate = new EntityPredicate()
		.setDistance(this.distance)
		.allowInvulnerable()
		.allowFriendlyFire()
		.setLineOfSiteRequired();

	world.getTargettableEntitiesWithinAABB(this.creature.getClass(), predicate, this.creature, aabb)
		.stream()
		.filter(creature -> this.ibreed.canMateWith(creature))
		.sorted(Comparator.comparing(creature -> this.creature.getDistanceSq(creature)))
		.findFirst()
		.ifPresent(creature -> this.mate = creature);

	return this.mate != null;
    }

    @Override
    public void resetTask() {
	this.mate = null;
	this.runDelay = 0;
    }

    @Override
    public boolean shouldContinueExecuting() {
	IBreed breed = this.mate.getCapability(BREED_CAPABILITY).orElseThrow(IllegalArgumentException::new);

	return this.mate.isAlive() && breed.isInLove() && this.runDelay < 60;
    }

    @Override
    public boolean shouldExecute() {

	if (!this.ibreed.isInLove())
	    return false;

	return this.getNearbyMate();
    }

    protected void spawnBaby() {
	IAge iage = this.mate.getCapability(AGE_CAPABILITY).orElseThrow(IllegalArgumentException::new);
	IBreed ibreed = this.mate.getCapability(BREED_CAPABILITY).orElseThrow(IllegalArgumentException::new);

	int adultAge = this.getAdultAge();
	PlayerEntity loveCause = this.ibreed.getLoveCause();
	PlayerEntity player = loveCause != null ? loveCause : ibreed.getLoveCause();

	CreatureEntity child = this.iage.createChild(this.mate);
	World world = child.getEntityWorld();

	if (ModEventFactory.onBabyEntitySpawn(this.creature, this.mate, child, player)) {
	    this.iage.setGrowingAge(adultAge);
	    this.ibreed.resetInLove();

	    iage.setGrowingAge(adultAge);
	    ibreed.resetInLove();

	} else if (child != null) {

	    if (player != null)
		// TODO CriteriaTriggers
		player.addStat(ANIMALS_BRED);

	    this.iage.setGrowingAge(adultAge);
	    iage.setGrowingAge(adultAge);

	    this.ibreed.resetInLove();
	    ibreed.resetInLove();

	    double x = this.creature.posX;
	    double y = this.creature.posY;
	    double z = this.creature.posZ;

	    child.getCapability(AGE_CAPABILITY).ifPresent(iage1 -> iage1.setGrowingAge(this.getChildAge()));
	    child.getCapability(TAME_CAPABILITY).ifPresent(itame -> itame.setTamedBy(player));

	    child.setLocationAndAngles(x, y, z, 0, 0);

	    world.addEntity(child);
	    world.setEntityState(this.creature, (byte) 18);

	    if (world.getGameRules().getBoolean(DO_MOB_LOOT))
		world.addEntity(new ExperienceOrbEntity(world, x, y, z, this.getExperience()));
	}
    }

    @Override
    public void tick() {
	float verticalSpeed = this.creature.getVerticalFaceSpeed();

	this.creature.getLookController().setLookPositionWithEntity(this.mate, 10F, verticalSpeed);
	this.creature.getNavigator().tryMoveToEntityLiving(this.mate, this.speed);

	++this.runDelay;

	if (this.runDelay >= 60 && this.creature.getDistanceSq(this.mate) < this.distance)
	    this.spawnBaby();
    }
}
