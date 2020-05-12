package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import java.util.Random;

import github.kingvampire.DeepTrenches.core.entity.StaspEntity;

public class ExtractAqueanSapGoal extends MoveToBlockThenExecuteGoal {

    public ExtractAqueanSapGoal(StaspEntity staspEntity, MoveToRandomBlockGoal findBlockGoal) {
	super(staspEntity, findBlockGoal);
    }

    @Override
    public boolean shouldExecute() {
	int aqueanSap = this.staspEntity.getAqueanSap();

	return !this.staspEntity.hasPollen() && aqueanSap < 100 && super.shouldExecute();
    }

    @Override
    protected int getRunDelay(Random rand) {
	return 0;
    }

    @Override
    protected void onFinished() {
	Random random = this.staspEntity.getRNG();

	int current = this.staspEntity.getAqueanSap();
	int sap = current + 25 + random.nextInt(15);

	if (sap >= 100)
	    this.staspEntity.setAqueanSap(100);
	else
	    this.staspEntity.setAqueanSap(sap);

	this.staspEntity.setTicksSincePollination(0);
    }

}
