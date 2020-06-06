package github.kingvampire.DeepTrenches.core.entity.goals.loosejaw;

import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;

import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.entity.DragonfishEntity;
import github.kingvampire.DeepTrenches.api.entity.goals.PreyingGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraftforge.common.util.LazyOptional;

public class LoosejawPreyingGoal<T extends AbstractFishEntity> extends PreyingGoal<T> {

    public LoosejawPreyingGoal(DragonfishEntity dragonfish, Class<T> targetClass) {
	super(dragonfish, targetClass);
    }

    @Override
    public boolean shouldExecute() {
	DragonfishEntity dragonfish = (DragonfishEntity) this.goalOwner;
	LazyOptional<ITame> tame = dragonfish.getCapability(TAME_CAPABILITY);

	if (tame.isPresent()) {
	    ITame itame = tame.orElseThrow(IllegalArgumentException::new);

	    if (itame.isTamed() && !itame.isSitting())
		return false;

	    return !dragonfish.isLuring() && super.shouldExecute();
	}

	return false;
    }

}
