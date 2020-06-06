package github.kingvampire.DeepTrenches.core.entity.goals.dragonfish;

import github.kingvampire.DeepTrenches.api.entity.DragonfishEntity;
import net.minecraft.entity.EntityPredicate;

public class RespondBlinkGoal extends BlinkGoal {

    public RespondBlinkGoal(DragonfishEntity dragonfish) {
	super(dragonfish);
    }

    @Override
    protected EntityPredicate getEntityPredicate() {
	double distance = this.getTargetDistance();

	return new EntityPredicate().setDistance(distance).setCustomPredicate(living -> {
	    DragonfishEntity dragonfish = (DragonfishEntity) living;

	    if (living == this.goalOwner)
		return false;

	    return dragonfish.isBlinking();
	});
    }

    @Override
    public boolean shouldExecute() {
	DragonfishEntity dragonfish = (DragonfishEntity) this.goalOwner;

	if (!this.ianger.isAngry() && !this.itame.isSitting() && !dragonfish.isLuring() && !dragonfish.isBlinking()) {
	    this.findNearestTarget();

	    return this.target != null;
	}

	return false;
    }

}
