package github.kingvampire.DeepTrenches.core.entity.goals.betta;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.entity.goals.ChaseGoal;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;

public class BettaChaseGoal extends ChaseGoal {

    private final IGroup igroup;
    private final ITame itame;

    public BettaChaseGoal(BettaEntity bettaEntity) {
	super(bettaEntity);

	this.igroup = bettaEntity.getCapability(GROUP_CAPABILITY).orElseThrow(IllegalArgumentException::new);
	this.itame = bettaEntity.getCapability(TAME_CAPABILITY).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean shouldExecute() {
	return !this.itame.isTamed() && this.igroup.isGroupLeader() && super.shouldExecute();
    }

    @Override
    public boolean shouldContinueExecuting() {
	LivingEntity target = this.goalOwner.getAttackTarget();

	if (target instanceof CreatureEntity) {
	    Set<CreatureEntity> members = this.igroup.getMembers();
	    CreatureEntity creature = (CreatureEntity) target;

	    if (!members.contains(creature))
		return super.shouldContinueExecuting();
	}

	return false;
    }

    @Override
    public void startExecuting() {
	super.startExecuting();

	this.goalOwner.getCapability(ANGER_CAPABILITY).ifPresent(ianger -> ianger.setAnger(this.target));
    }

}
