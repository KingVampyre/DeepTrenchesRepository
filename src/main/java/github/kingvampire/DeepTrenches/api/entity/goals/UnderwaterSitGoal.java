package github.kingvampire.DeepTrenches.api.entity.goals;

import static net.minecraft.entity.ai.goal.Goal.Flag.MOVE;

import java.util.EnumSet;

import net.minecraft.entity.CreatureEntity;

public class UnderwaterSitGoal extends SitGoal {

    public UnderwaterSitGoal(CreatureEntity creature) {
	super(creature);

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
