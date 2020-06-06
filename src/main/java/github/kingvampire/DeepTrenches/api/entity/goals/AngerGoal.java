package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class AngerGoal extends HurtByTargetGoal {

    protected CreatureEntity creature;

    public AngerGoal(CreatureEntity creature) {
	super(creature);

	this.creature = creature;

	this.setCallsForHelp();
    }

    protected void alert(CreatureEntity creature) {
	this.setAttackTarget(creature, this.target);
    }

    @Override
    protected void alertOthers() {
	double range = this.creature.getAttribute(FOLLOW_RANGE).getBaseValue();

	BlockPos pos = this.creature.getPosition();
	World world = this.creature.getEntityWorld();

	AxisAlignedBB box = new AxisAlignedBB(pos).grow(range);

	for (CreatureEntity creature : world.getEntitiesWithinAABB(this.creature.getClass(), box)) {

	    if (this.shouldAlert(creature))
		this.alert(creature);

	}

    }

    @Override
    protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
	super.setAttackTarget(mobIn, targetIn);

	this.creature.getCapability(ANGER_CAPABILITY).ifPresent(ianger -> ianger.setAnger(targetIn));
    }

    protected boolean shouldAlert(CreatureEntity creature) {
	return true;
    }

    @Override
    public boolean shouldContinueExecuting() {
	LazyOptional<IAnger> anger = this.creature.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent())
	    return anger.orElseThrow(IllegalArgumentException::new).isAngry() && super.shouldContinueExecuting();

	return false;
    }

    @Override
    public boolean shouldExecute() {
	LivingEntity targetIn = this.creature.getRevengeTarget();
	int revengeTimer = this.creature.getRevengeTimer();

	if (targetIn != null)
	    return revengeTimer >= 0;

	return false;
    }

    @Override
    public void startExecuting() {
	super.startExecuting();

	LazyOptional<IAnger> anger = this.creature.getCapability(ANGER_CAPABILITY);

	if (anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    this.setAttackTarget(this.creature, this.target);
	    ianger.setAnger(this.target);
	}

    }

}
