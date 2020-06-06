package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.entity.HatchetfishEntity.RANDOM_SWIM_CHANCE;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;

public class RandomGroupSwimGoal extends RandomSwimmingGoal {

    protected final IGroup igroup;

    public RandomGroupSwimGoal(CreatureEntity creature) {
	this(creature, (int) creature.getAttribute(RANDOM_SWIM_CHANCE).getBaseValue());
    }

    public RandomGroupSwimGoal(CreatureEntity creature, int chance) {
	super(creature, creature.getAttribute(MOVEMENT_SPEED).getBaseValue(), chance);

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
