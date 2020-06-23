package github.kingvampire.DeepTrenches.api.entity.goals;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModAttributes.MOVEMENT_SPEED_BOOST;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.util.LazyOptional;

public class GroupPanicGoal extends PanicGoal {

    public GroupPanicGoal(CreatureEntity creature) {
	super(creature, creature.getAttribute(MOVEMENT_SPEED_BOOST).getValue());
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IAnger> anger = this.creature.getCapability(ANGER_CAPABILITY);
	LazyOptional<IGroup> group = this.creature.getCapability(GROUP_CAPABILITY);

	if (!this.creature.isBurning())
	    return false;

	if (this.creature.getRevengeTarget() == null) {

	    if (anger.isPresent()) {
		IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

		if (this.creature.isBurning() && ianger.isAngry())
		    return true;
	    }

	    return false;

	} else if (this.creature.isBurning()) {

	    if (group.isPresent()) {
		IGroup igroup = group.orElseThrow(IllegalArgumentException::new);

		if (igroup.hasGroupLeader()) {
		    Vec3d vec = igroup.getGroupLeader().getPositionVec();
		    Vec3d towards = RandomPositionGenerator.findRandomTargetBlockTowards(this.creature, 5, 4, vec);

		    if (towards != null) {
			this.randPosX = (double) towards.getX();
			this.randPosY = (double) towards.getY();
			this.randPosZ = (double) towards.getZ();

			return true;
		    }
		}
	    }

	    BlockPos pos = this.getRandPos(this.creature.world, this.creature, 5, 4);

	    if (pos != null) {
		this.randPosX = (double) pos.getX();
		this.randPosY = (double) pos.getY();
		this.randPosZ = (double) pos.getZ();

		return true;
	    }
	}

	return this.findRandomPosition();
    }

}
