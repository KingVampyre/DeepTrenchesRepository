package github.kingvampire.DeepTrenches.core.entity.goals.dragonfish;

import github.kingvampire.DeepTrenches.api.entity.DragonfishEntity;
import github.kingvampire.DeepTrenches.api.entity.goals.ChaseGoal;

public class DragonfishChaseGoal extends ChaseGoal {

    public DragonfishChaseGoal(DragonfishEntity dragonfish) {
	super(dragonfish);
    }

    @Override
    public boolean shouldExecute() {
	DragonfishEntity dragonfish = (DragonfishEntity) this.goalOwner;

	return !dragonfish.isLuring() && super.shouldExecute();
    }

}
