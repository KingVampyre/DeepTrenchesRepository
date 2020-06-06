package github.kingvampire.DeepTrenches.core.entity.goals.dragonfish;

import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;

import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.entity.DragonfishEntity;
import github.kingvampire.DeepTrenches.api.entity.LureGoal;
import net.minecraftforge.common.util.LazyOptional;

public class DragonfishLureGoal extends LureGoal {

    public DragonfishLureGoal(DragonfishEntity dragonfish) {
	super(dragonfish);
    }

    @Override
    public boolean shouldExecute() {
	DragonfishEntity dragonfish = (DragonfishEntity) this.goalOwner;
	LazyOptional<ITame> tame = dragonfish.getCapability(TAME_CAPABILITY);

	if (tame.isPresent()) {
	    ITame itame = tame.orElseThrow(IllegalArgumentException::new);

	    if (itame.isTamed() && !itame.isSitting())
		return false;

	    return !dragonfish.isBlinking() && super.shouldExecute();
	}

	return false;
    }

}
