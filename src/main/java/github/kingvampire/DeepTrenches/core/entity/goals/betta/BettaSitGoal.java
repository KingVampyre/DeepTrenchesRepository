package github.kingvampire.DeepTrenches.core.entity.goals.betta;

import static net.minecraft.entity.ai.goal.Goal.Flag.MOVE;

import java.util.EnumSet;

import github.kingvampire.DeepTrenches.api.entity.goals.SitGoal;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;

public class BettaSitGoal extends SitGoal {

    public BettaSitGoal(BettaEntity bettaEntity) {
	super(bettaEntity);

	this.setMutexFlags(EnumSet.of(MOVE));
    }

    @Override
    protected boolean canSit() {
	return this.creature.isInWater();
    }

    @Override
    public void startExecuting() {
	this.itame.setSitting(true);
    }

}
