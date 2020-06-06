package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;

import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import net.minecraft.entity.CreatureEntity;
import net.minecraftforge.common.util.LazyOptional;

public class GroupAngerGoal extends AngerGoal {

    public GroupAngerGoal(CreatureEntity creature) {
	super(creature);
    }

    @Override
    protected void alert(CreatureEntity creature) {
	LazyOptional<IGroup> group = creature.getCapability(GROUP_CAPABILITY);

	group.ifPresent(igroup -> this.setAttackTarget(creature, this.target));
    }

    @Override
    protected boolean shouldAlert(CreatureEntity creature) {
	LazyOptional<IGroup> group = creature.getCapability(GROUP_CAPABILITY);
	LazyOptional<ITame> tame = creature.getCapability(TAME_CAPABILITY);

	if (tame.isPresent())
	    return tame.orElseThrow(IllegalArgumentException::new).isTamed();

	if (group.isPresent())
	    return group.orElseThrow(IllegalArgumentException::new).getMembers().contains(this.creature);

	return false;
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IGroup> group = creature.getCapability(GROUP_CAPABILITY);

	if (group.isPresent())
	    return group.orElseThrow(IllegalArgumentException::new).hasGroupLeader();

	return false;
    }

}
