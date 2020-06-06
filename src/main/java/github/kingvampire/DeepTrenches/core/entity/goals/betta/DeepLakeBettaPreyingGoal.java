package github.kingvampire.DeepTrenches.core.entity.goals.betta;

import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.PREY_DETECTION;

import github.kingvampire.DeepTrenches.core.entity.DeepLakeBettaEntity;

public class DeepLakeBettaPreyingGoal extends BettaPreyingGoal {

    public DeepLakeBettaPreyingGoal(DeepLakeBettaEntity deepLakeBetta) {
	super(deepLakeBetta);
    }

    @Override
    protected double getTargetDistance() {
	return this.goalOwner.getAttribute(PREY_DETECTION).getValue();
    }

    @Override
    public boolean shouldExecute() {
	DeepLakeBettaEntity bettaEntity = (DeepLakeBettaEntity) this.goalOwner;

	return !bettaEntity.isLuring() && super.shouldExecute();
    }

}
