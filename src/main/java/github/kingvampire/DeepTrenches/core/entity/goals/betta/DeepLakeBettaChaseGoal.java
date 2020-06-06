package github.kingvampire.DeepTrenches.core.entity.goals.betta;

import github.kingvampire.DeepTrenches.core.entity.DeepLakeBettaEntity;

public class DeepLakeBettaChaseGoal extends BettaChaseGoal {

    public DeepLakeBettaChaseGoal(DeepLakeBettaEntity deepLakeBetta) {
	super(deepLakeBetta);

    }

    @Override
    public boolean shouldExecute() {
	DeepLakeBettaEntity deepLakeBetta = (DeepLakeBettaEntity) this.goalOwner;

	return !deepLakeBetta.isLuring() && super.shouldExecute();
    }

}
