package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class StaspAttackGoal extends MeleeAttackGoal {

	public StaspAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
	}

	@Override
	public boolean shouldContinueExecuting() {
		StaspEntity staspEntity = (StaspEntity) this.attacker;

		return staspEntity.isAngry() && super.shouldContinueExecuting();
	}

	@Override
	public boolean shouldExecute() {
		StaspEntity staspEntity = (StaspEntity) this.attacker;

		return staspEntity.isAngry() && super.shouldExecute();
	}

}
