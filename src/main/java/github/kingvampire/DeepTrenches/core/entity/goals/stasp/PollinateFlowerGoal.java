package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import java.util.Random;

import github.kingvampire.DeepTrenches.core.entity.StaspEntity;

public class PollinateFlowerGoal extends MoveToBlockThenExecuteGoal {

    public PollinateFlowerGoal(StaspEntity staspEntity, MoveToRandomBlockGoal findBlockGoal) {
	super(staspEntity, findBlockGoal);
    }

    @Override
    public boolean shouldExecute() {
	int pollen = this.staspEntity.getPollen();

	return !this.staspEntity.hasAqueanSap() && pollen < 100 && super.shouldExecute();
    }

    @Override
    protected int getRunDelay(Random rand) {
	return 200;
    }

    @Override
    protected void onFinished() {
	Random random = this.staspEntity.getRNG();

	int current = this.staspEntity.getPollen();
	int pollen = current + 25 + random.nextInt(15);

	if (pollen >= 100)
	    this.staspEntity.setPollen(100);
	else
	    this.staspEntity.setPollen(pollen);

	this.staspEntity.setTicksSincePollination(0);
    }

}
