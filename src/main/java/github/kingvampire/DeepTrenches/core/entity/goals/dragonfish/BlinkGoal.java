package github.kingvampire.DeepTrenches.core.entity.goals.dragonfish;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.BLINKING_DELAY;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.BLINK_INTERVAL;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.BLINK_RANGE;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.MAX_BLINKS;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.MIN_BLINKS;

import java.util.Random;

import com.google.common.base.Predicates;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.entity.DragonfishEntity;
import github.kingvampire.DeepTrenches.api.entity.LureGoal;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class BlinkGoal extends TargetGoal {

    protected final IAnger ianger;
    protected final ITame itame;

    protected final int blinkingDelay;
    protected final int blinkInterval;
    protected final int maxBlinkings;
    protected final int minBlinkings;

    protected int blinkings;
    protected boolean isBlinking;
    protected int runDelay;
    protected int ticksUnlit;

    public BlinkGoal(DragonfishEntity dragonfish) {
	super(dragonfish, false);

	this.ianger = this.goalOwner.getCapability(ANGER_CAPABILITY).orElseThrow(IllegalArgumentException::new);
	this.itame = this.goalOwner.getCapability(TAME_CAPABILITY).orElseThrow(IllegalArgumentException::new);

	this.blinkingDelay = (int) this.goalOwner.getAttribute(BLINKING_DELAY).getValue();
	this.blinkInterval = (int) this.goalOwner.getAttribute(BLINK_INTERVAL).getValue();
	this.maxBlinkings = (int) this.goalOwner.getAttribute(MAX_BLINKS).getValue();
	this.minBlinkings = (int) this.goalOwner.getAttribute(MIN_BLINKS).getValue();
    }

    protected void findNearestTarget() {
	double distance = this.getTargetDistance();

	double x = this.goalOwner.posX;
	double y = this.goalOwner.posY + this.goalOwner.getEyeHeight();
	double z = this.goalOwner.posZ;

	AxisAlignedBB box = this.goalOwner.getBoundingBox().grow(distance);
	EntityPredicate predicate = this.getEntityPredicate();
	World world = this.goalOwner.getEntityWorld();

	this.target = world.getClosestEntityWithinAABB(DragonfishEntity.class, predicate, null, x, y, z, box);
    }

    protected EntityPredicate getEntityPredicate() {
	double distance = this.getTargetDistance();

	return new EntityPredicate().setDistance(distance).setCustomPredicate(Predicates.not(this.goalOwner::equals));
    }

    private int getBlinkings(Random rand) {
	return this.minBlinkings + rand.nextInt(this.maxBlinkings - this.minBlinkings + 1);
    }

    @Override
    protected double getTargetDistance() {
	return this.goalOwner.getAttribute(BLINK_RANGE).getValue();
    }

    public boolean isBlinking() {
	return this.isBlinking;
    }

    @Override
    public void resetTask() {
	this.setIsBlinking(false);
    }

    public void setIsBlinking(boolean isBlinking) {
	this.isBlinking = isBlinking;
    }

    public boolean shouldBlink() {
	return this.isBlinking && this.ticksUnlit == 0;
    }

    @Override
    public boolean shouldContinueExecuting() {

	if (!this.ianger.isAngry() && !this.itame.isSitting() && this.blinkings > 0) {

	    if (!this.target.isAlive())
		return false;
	    else if (this.goalOwner.getDistanceSq(this.target) > Math.pow(this.getTargetDistance(), 2))
		return false;
	    else
		return true;
	}

	return false;
    }

    @Override
    public boolean shouldExecute() {
	DragonfishEntity dragonfish = (DragonfishEntity) this.goalOwner;

	if (!this.ianger.isAngry() && !this.itame.isSitting()) {

	    if (!dragonfish.isLuring() && this.runDelay > 0) {
		--this.runDelay;

		return false;
	    }

	    this.runDelay = this.blinkingDelay;
	    this.findNearestTarget();

	    return this.target != null;
	}

	return false;
    }

    @Override
    public void startExecuting() {
	super.startExecuting();

	DragonfishEntity dragonfish = (DragonfishEntity) this.goalOwner;
	LureGoal lureGoal = dragonfish.getLureGoal();

	this.blinkings = this.getBlinkings(this.goalOwner.getRNG());

	this.setIsBlinking(true);
	lureGoal.setIsLuring(false);
    }

    @Override
    public void tick() {

	if (this.ticksUnlit > 0)
	    --this.ticksUnlit;

	else if (this.ticksUnlit == 0) {
	    this.ticksUnlit = this.blinkInterval;

	    --this.blinkings;
	}

	DragonfishEntity dragonfish = (DragonfishEntity) this.target;

	this.goalOwner.getLookController().setLookPositionWithEntity(dragonfish, 0, 0);
	dragonfish.getLookController().setLookPositionWithEntity(this.goalOwner, 0, 0);

    }

}
