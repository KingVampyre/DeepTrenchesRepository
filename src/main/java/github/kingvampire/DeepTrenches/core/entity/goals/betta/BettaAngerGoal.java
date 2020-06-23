package github.kingvampire.DeepTrenches.core.entity.goals.betta;

import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;

import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.entity.goals.AngerGoal;
import net.minecraft.entity.CreatureEntity;
import net.minecraftforge.common.util.LazyOptional;

public class BettaAngerGoal extends AngerGoal {

    public BettaAngerGoal(CreatureEntity creature) {
	super(creature);
    }

    @Override
    protected void alert(CreatureEntity creature) {
	LazyOptional<IGroup> group = creature.getCapability(GROUP_CAPABILITY);

	if (group.isPresent()) {
	    IGroup igroup = group.orElseThrow(IllegalArgumentException::new);

	    if (igroup.hasGroupLeader())
		this.setAttackTarget(igroup.getGroupLeader(), this.target);
	}

    }

    @Override
    protected boolean shouldAlert(CreatureEntity creature) {
	LazyOptional<IGroup> group = creature.getCapability(GROUP_CAPABILITY);
	LazyOptional<ITame> tame = creature.getCapability(TAME_CAPABILITY);

	if (tame.isPresent())
	    return !tame.orElseThrow(IllegalArgumentException::new).isTamed();

	if (group.isPresent())
	    return group.orElseThrow(IllegalArgumentException::new).hasGroupLeader();

	return false;
    }

}
