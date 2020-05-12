package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraftforge.common.util.LazyOptional;

public class AngerGoal extends HurtByTargetGoal {

    public AngerGoal(CreatureEntity creature) {
	super(creature, creature.getClass());

	this.setCallsForHelp();
    }

    @Override
    protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
	super.setAttackTarget(mobIn, targetIn);

	this.goalOwner.getCapability(ANGER_CAPABILITY).ifPresent(ianger -> ianger.setAnger(targetIn));
    }

    @Override
    public boolean shouldContinueExecuting() {
	LazyOptional<IAnger> anger = this.goalOwner.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent())
	    return anger.orElseThrow(IllegalArgumentException::new).isAngry() && super.shouldContinueExecuting();

	return false;
    }

    @Override
    public boolean shouldExecute() {
	LivingEntity targetIn = this.goalOwner.getRevengeTarget();
	int revengeTimer = this.goalOwner.getRevengeTimer();

	if (targetIn != null)
	    return targetIn instanceof BettaEntity && revengeTimer >= 0;

	return false;
    }

    @Override
    public void startExecuting() {
	super.startExecuting();

	this.setAttackTarget(this.goalOwner, this.target);
    }

}
