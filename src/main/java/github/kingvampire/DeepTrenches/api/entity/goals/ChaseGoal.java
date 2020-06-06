package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.entity.HatchetfishEntity.MOVEMENT_SPEED_BOOST;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;
import static net.minecraft.entity.ai.goal.Goal.Flag.MOVE;

import java.util.EnumSet;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraftforge.common.util.LazyOptional;

public class ChaseGoal extends AngerGoal {

    protected final CreatureEntity creature;
    protected final double speed;

    protected LivingEntity target;

    public ChaseGoal(CreatureEntity creature) {
	super(creature);

	this.creature = creature;
	this.speed = creature.getAttribute(MOVEMENT_SPEED_BOOST).getValue();

	this.setMutexFlags(EnumSet.of(MOVE));
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IAnger> anger = this.goalOwner.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    if (ianger.isAngry()) {
		this.target = this.creature.getAttackTarget();

		if (this.target == null)
		    return false;

		double distance = creature.getAttribute(FOLLOW_RANGE).getValue();

		if (this.target.getDistanceSq(this.creature) > Math.pow(distance, 2))
		    return false;

		return true;
	    }
	}

	return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
	LazyOptional<IAnger> anger = this.goalOwner.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    if (ianger.isAngry()) {
		double distance = creature.getAttribute(FOLLOW_RANGE).getValue();
		PathNavigator navigator = this.creature.getNavigator();

		if (!navigator.noPath() && this.target.isAlive())
		    return this.target.getDistanceSq(this.creature) < Math.pow(distance, 2);
	    }
	}

	return false;
    }

    @Override
    public void resetTask() {
	this.target = null;
    }

    @Override
    public void startExecuting() {
	this.creature.getNavigator().tryMoveToXYZ(this.target.posX, this.target.posX, this.target.posX, this.speed);
    }

}
