package github.kingvampire.DeepTrenches.core.entity.goals.betta;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;

import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.util.math.AxisAlignedBB;

public class BettaNearestAttackableTargetGoal extends NearestAttackableTargetGoal<BettaEntity> {

    private final IGroup igroup;
    private final ITame itame;

    private double targetChance;

    public BettaNearestAttackableTargetGoal(BettaEntity bettaEntity, double targetChance) {
	super(bettaEntity, BettaEntity.class, 0, false, true, null);

	this.targetChance = targetChance;

	this.igroup = bettaEntity.getCapability(GROUP_CAPABILITY).orElseThrow(IllegalArgumentException::new);
	this.itame = bettaEntity.getCapability(TAME_CAPABILITY).orElseThrow(IllegalArgumentException::new);

	this.targetEntitySelector
		.setCustomPredicate(living -> !this.igroup.getGroup().contains(CreatureEntity.class.cast(living)));
    }

    @Override
    protected AxisAlignedBB getTargetableArea(double targetDistance) {
	return this.goalOwner.getBoundingBox().grow(targetDistance);
    }

    private boolean isInSameGroup(LivingEntity livingEntity) {

	if (livingEntity instanceof CreatureEntity)
	    return !this.igroup.getGroup().contains(CreatureEntity.class.cast(livingEntity));

	return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
	LivingEntity target = this.goalOwner.getAttackTarget();

	return !this.isInSameGroup(target) && super.shouldContinueExecuting();
    }

    @Override
    public boolean shouldExecute() {

	if (!this.itame.isTamed()) {

	    if (this.igroup.isGroupLeader())
		if (this.goalOwner.getRNG().nextInt(100) < this.targetChance)
		    return false;
		else
		    this.findNearestTarget();

	    return this.nearestTarget != null;
	}

	return false;
    }

    @Override
    public void startExecuting() {
	super.startExecuting();

	this.goalOwner.getCapability(ANGER_CAPABILITY).ifPresent(ianger -> ianger.setAnger(this.nearestTarget));
    }

}
