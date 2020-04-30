package github.kingvampire.DeepTrenches.api.entity.goals;

import java.util.Random;

import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.entity.ai.goal.Goal;

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
		int ticks = this.staspEntity.getTicksSincePollination();

		return !this.staspEntity.isAngry() && this.findBlockGoal.getIsAboveDestination() && ticks > 200;
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
