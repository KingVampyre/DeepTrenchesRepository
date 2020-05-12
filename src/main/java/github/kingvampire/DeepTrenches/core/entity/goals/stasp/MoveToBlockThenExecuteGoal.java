package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraftforge.common.util.LazyOptional;

public abstract class MoveToBlockThenExecuteGoal extends Goal {

    protected MoveToRandomBlockGoal findBlockGoal;
    protected int runDelay;
    protected StaspEntity staspEntity;

    public MoveToBlockThenExecuteGoal(StaspEntity staspEntity, MoveToRandomBlockGoal findBlockGoal) {
	this.findBlockGoal = findBlockGoal;
	this.staspEntity = staspEntity;
    }

    protected abstract int getRunDelay(Random rand);

    protected abstract void onFinished();

    @Override
    public boolean shouldContinueExecuting() {
	return !(this.runDelay <= 0) && this.shouldExecute();
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IAnger> anger = this.staspEntity.getCapability(ANGER_CAPABILITY);

	int ticks = this.staspEntity.getTicksSincePollination();

	if (anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    return !ianger.isAngry() && this.findBlockGoal.getIsAboveDestination() && ticks > 200;
	}

	return false;
    }

    @Override
    public void startExecuting() {
	Random rand = this.staspEntity.getRNG();

	this.runDelay = this.getRunDelay(rand);
    }

    @Override
    public void tick() {
	--this.runDelay;

	if (this.runDelay <= 0) {
	    this.onFinished();
	}
    }

}
