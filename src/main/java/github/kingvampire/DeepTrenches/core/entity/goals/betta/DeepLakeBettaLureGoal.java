package github.kingvampire.DeepTrenches.core.entity.goals.betta;

import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.entity.DragonfishEntity.PREY_DETECTION;

import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.entity.LureGoal;
import github.kingvampire.DeepTrenches.core.entity.DeepLakeBettaEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;

public class DeepLakeBettaLureGoal extends LureGoal {

    private final IGroup igroup;
    private final ITame itame;

    public DeepLakeBettaLureGoal(DeepLakeBettaEntity deepLakeBetta) {
	super(deepLakeBetta);

	this.igroup = this.goalOwner.getCapability(GROUP_CAPABILITY).orElseThrow(IllegalArgumentException::new);
	this.itame = this.goalOwner.getCapability(TAME_CAPABILITY).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    protected double getDetectionRange() {
	return this.goalOwner.getAttribute(PREY_DETECTION).getValue();
    }

    @Override
    protected EntityPredicate getEntityPredicate(double distance) {
	return super.getEntityPredicate(distance)
		.setCustomPredicate(living -> !this.igroup.getMembers().contains(CreatureEntity.class.cast(living)));
    }

    private boolean isInSameGroup(LivingEntity livingEntity) {

	if (livingEntity instanceof CreatureEntity)
	    return !this.igroup.getMembers().contains(CreatureEntity.class.cast(livingEntity));

	return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
	LivingEntity target = this.goalOwner.getAttackTarget();

	return !this.isInSameGroup(target) && super.shouldContinueExecuting();
    }

    @Override
    public boolean shouldExecute() {
	return this.igroup.isGroupLeader() && !this.itame.isTamed() && super.shouldExecute();
    }

}
