package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;

import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import net.minecraft.entity.CreatureEntity;

public class UntamedFollowGroupLeaderGoal extends FollowGroupLeaderGoal {

    private final ITame itame;

    public UntamedFollowGroupLeaderGoal(CreatureEntity creature) {
	super(creature);

	this.itame = creature.getCapability(TAME_CAPABILITY).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean shouldExecute() {
	return !this.itame.isTamed() ? super.shouldExecute() : false;
    }

}
