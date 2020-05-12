package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static net.minecraft.entity.ai.goal.Goal.Flag.JUMP;
import static net.minecraft.entity.ai.goal.Goal.Flag.MOVE;

import java.util.EnumSet;

import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;

public class SitGoal extends Goal {

    protected CreatureEntity creature;
    protected boolean isSitting;
    protected ITame itame;

    public SitGoal(CreatureEntity creature) {
	this.creature = creature;
	this.itame = creature.getCapability(TAME_CAPABILITY).orElseThrow(IllegalArgumentException::new);

	this.setMutexFlags(EnumSet.of(JUMP, MOVE));
    }

    protected boolean canSit() {
	return this.creature.onGround;
    }

    public boolean isSitting() {
	return this.isSitting;
    }

    @Override
    public void resetTask() {
	this.itame.setSitting(false);
    }

    public void setSitting(boolean isSitting) {
	this.isSitting = isSitting;
    }

    @Override
    public boolean shouldExecute() {

	if (!this.itame.isTamed())
	    return false;

	if (this.creature.isInWaterOrBubbleColumn())
	    return false;

	if (!this.canSit())
	    return false;

	PlayerEntity player = this.itame.getOwner();

	if (player == null)
	    return true;

	if (this.creature.getDistanceSq(player) < 144)
	    return player.getRevengeTarget() != null ? false : this.isSitting;

	return false;
    }

    @Override
    public void startExecuting() {
	this.creature.getNavigator().clearPath();

	this.itame.setSitting(true);
    }

}
