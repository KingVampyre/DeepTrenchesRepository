package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;

import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;

public class RandomSwimGroupGoal extends RandomSwimmingGoal {

    private final IGroup igroup;

    public RandomSwimGroupGoal(CreatureEntity creature, double speed, int chance) {
	super(creature, speed, chance);

	this.igroup = creature.getCapability(GROUP_CAPABILITY).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean shouldExecute() {

	if (this.igroup.isAlone()) {
	    this.makeUpdate();

	    return super.shouldExecute();
	}

	if (this.igroup.isGroupLeader()) {
	    this.makeUpdate();

	    return super.shouldExecute();
	}

	return false;
    }

}
