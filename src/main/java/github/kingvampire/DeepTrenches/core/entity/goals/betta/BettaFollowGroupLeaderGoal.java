package github.kingvampire.DeepTrenches.core.entity.goals.betta;

import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;

import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.entity.goals.FollowGroupLeaderGoal;
import net.minecraft.entity.CreatureEntity;

public class BettaFollowGroupLeaderGoal extends FollowGroupLeaderGoal {

    private final ITame itame;

    public BettaFollowGroupLeaderGoal(CreatureEntity creature, double speed) {
	super(creature, speed);

	this.itame = creature.getCapability(TAME_CAPABILITY).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean shouldExecute() {
	return !this.itame.isTamed() ? super.shouldExecute() : false;
    }

}
