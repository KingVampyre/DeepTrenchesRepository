package github.kingvampire.DeepTrenches.api.entity;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.BLINK_RANGE;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.LURE_ATTRACTION;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.LURE_DELAY;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.LURE_MAX_LIT;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.LURE_MAX_UNLIT;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.LURE_MIN_LIT;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.LURE_MIN_UNLIT;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.MAX_LURING;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.MIN_LURING;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.PREY_DETECTION;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

import java.util.Random;

import com.google.common.base.Predicates;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class LureGoal extends TargetGoal {

    private final ITame itame;
    private final IAnger ianger;

    private final int lureDelay;
    private final int maxLitInterval;
    private final int maxLuring;
    private final int maxUnlitInterval;
    private final int minLitInterval;
    private final int minLuring;
    private final int minUnlitInterval;

    private boolean isLuring;
    private int litInterval;
    private int luring;
    private int runDelay;
    private int unlitInterval;

    public LureGoal(CreatureEntity creature) {
	super(creature, false);

	this.ianger = this.goalOwner.getCapability(ANGER_CAPABILITY).orElseThrow(IllegalArgumentException::new);
	this.itame = this.goalOwner.getCapability(TAME_CAPABILITY).orElseThrow(IllegalArgumentException::new);

	this.maxLitInterval = (int) this.goalOwner.getAttribute(LURE_MAX_LIT).getValue();
	this.minLitInterval = (int) this.goalOwner.getAttribute(LURE_MIN_LIT).getValue();
	this.maxUnlitInterval = (int) this.goalOwner.getAttribute(LURE_MAX_UNLIT).getValue();
	this.minUnlitInterval = (int) this.goalOwner.getAttribute(LURE_MIN_UNLIT).getValue();
	this.lureDelay = (int) this.goalOwner.getAttribute(LURE_DELAY).getValue();
	this.maxLuring = (int) this.goalOwner.getAttribute(MAX_LURING).getValue();
	this.minLuring = (int) this.goalOwner.getAttribute(MIN_LURING).getValue();
    }

    protected void findNearestTarget() {
	double distance = this.getTargetDistance();
	float eye = this.goalOwner.getEyeHeight();

	double x = this.goalOwner.posX;
	double y = this.goalOwner.posY;
	double z = this.goalOwner.posZ;

	AxisAlignedBB aabb = this.goalOwner.getBoundingBox().grow(distance);
	EntityPredicate predicate = this.getEntityPredicate(distance);
	World world = this.goalOwner.getEntityWorld();

	this.target = world.getClosestEntityWithinAABB(AbstractFishEntity.class, predicate, null, x, y + eye, z, aabb);
    }

    protected EntityPredicate getEntityPredicate(double distance) {
	return new EntityPredicate().setDistance(distance).setCustomPredicate(Predicates.not(this.goalOwner::equals));
    }

    protected double getDetectionRange() {
	IAttribute attribute = this.target instanceof DragonfishEntity ? BLINK_RANGE : PREY_DETECTION;

	return this.goalOwner.getAttribute(attribute).getValue();
    }

    private int getLitInterval(Random rand) {
	return this.minLitInterval + rand.nextInt(this.maxLitInterval - this.minLitInterval + 1);
    }

    private int getLuring(Random rand) {
	return this.minLuring + rand.nextInt(this.maxLuring - this.minLuring + 1);
    }

    @Override
    protected double getTargetDistance() {
	return this.goalOwner.getAttribute(LURE_ATTRACTION).getValue();
    }

    private int getUnlitInterval(Random rand) {
	return this.minUnlitInterval + rand.nextInt(this.maxUnlitInterval - this.minUnlitInterval + 1);
    }

    public boolean isLuring() {
	return this.isLuring;
    }

    @Override
    public void resetTask() {
	this.setIsLuring(this.target instanceof DragonfishEntity);
	super.resetTask();
    }

    public void setIsLuring(boolean isLuring) {
	this.isLuring = isLuring;
    }

    @Override
    public boolean shouldContinueExecuting() {

	if (!this.ianger.isAngry() && !this.itame.isSitting() && this.luring > 0) {
	    double distance = this.getTargetDistance();
	    double range = this.getDetectionRange();
	    Team team = this.goalOwner.getTeam();

	    if (!this.target.isAlive())
		return false;
	    else if (team != null && this.target.getTeam() == team)
		return false;
	    else if (this.goalOwner.getDistanceSq(this.target) > distance * distance)
		return false;
	    else if (this.goalOwner.getDistanceSq(this.target) <= Math.pow(range, 2))
		return false;
	    else
		return true;
	}

	return false;
    }

    @Override
    public boolean shouldExecute() {

	if (!this.ianger.isAngry() && !this.itame.isSitting()) {

	    if (this.runDelay > 0) {
		--this.runDelay;

		return false;
	    }

	    this.runDelay = this.lureDelay;
	    this.findNearestTarget();

	    return this.target != null;
	}

	return false;
    }

    public boolean shouldLure() {
	return this.isLuring && this.litInterval > 0;
    }

    @Override
    public void startExecuting() {
	super.startExecuting();

	Random rand = this.goalOwner.getRNG();

	this.luring = this.getLuring(rand);
	this.unlitInterval = this.getUnlitInterval(rand);

	this.setIsLuring(true);
    }

    @Override
    public void tick() {
	CreatureEntity creature = (CreatureEntity) this.target;
	double speed = creature.getAttribute(MOVEMENT_SPEED).getValue();

	if (this.litInterval > 0) {
	    --this.litInterval;

	    if (this.litInterval == 0) {
		this.unlitInterval = this.getUnlitInterval(creature.getRNG());

		--this.luring;
	    }

	} else if (this.unlitInterval > 0) {
	    --this.unlitInterval;

	    if (this.unlitInterval == 0)
		this.litInterval = this.getLitInterval(creature.getRNG());

	}

	this.goalOwner.getLookController().setLookPositionWithEntity(creature, 0, 0);
	creature.getNavigator().tryMoveToEntityLiving(this.goalOwner, speed);

    }

}
