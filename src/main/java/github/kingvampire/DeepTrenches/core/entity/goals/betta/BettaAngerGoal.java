package github.kingvampire.DeepTrenches.core.entity.goals.betta;

import static github.kingvampire.DeepTrenches.api.capabilities.group.GroupProvider.GROUP_CAPABILITY;
import static github.kingvampire.DeepTrenches.api.capabilities.tame.TameProvider.TAME_CAPABILITY;
import static net.minecraft.entity.SharedMonsterAttributes.FOLLOW_RANGE;

import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.entity.goals.AngerGoal;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public class BettaAngerGoal extends AngerGoal {

    public BettaAngerGoal(CreatureEntity creature) {
	super(creature);
    }

    @Override
    protected void alertOthers() {
	double range = this.goalOwner.getAttribute(FOLLOW_RANGE).getBaseValue();
	LivingEntity target = this.goalOwner.getAttackTarget();

	BlockPos pos = this.goalOwner.getPosition();
	World world = this.goalOwner.getEntityWorld();

	AxisAlignedBB box = new AxisAlignedBB(pos).grow(range);

	for (BettaEntity betta : world.func_225317_b(BettaEntity.class, box)) {
	    LazyOptional<IGroup> group = betta.getCapability(GROUP_CAPABILITY);
	    LazyOptional<ITame> tame = betta.getCapability(TAME_CAPABILITY);

	    if (tame.isPresent() && tame.orElseThrow(IllegalArgumentException::new).isTamed())
		continue;

	    if (group.isPresent()) {
		IGroup igroup = group.orElseThrow(IllegalArgumentException::new);

		if (!igroup.hasGroupLeader())
		    continue;

		this.setAttackTarget(igroup.getGroupLeader(), target);
	    }
	}
    }

}
