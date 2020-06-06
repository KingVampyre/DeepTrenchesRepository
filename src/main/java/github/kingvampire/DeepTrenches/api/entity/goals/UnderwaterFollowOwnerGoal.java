package github.kingvampire.DeepTrenches.api.entity.goals;

import static net.minecraft.fluid.Fluids.FLOWING_WATER;
import static net.minecraft.fluid.Fluids.WATER;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UnderwaterFollowOwnerGoal extends FollowOwnerGoal {

    public UnderwaterFollowOwnerGoal(CreatureEntity creature, float minDist, float maxDist) {
	super(creature, minDist, maxDist);
    }

    @Override
    protected boolean canTeleportToBlock(BlockPos pos) {
	World world = this.creature.getEntityWorld();

	BlockPos up = pos.up();
	IFluidState fluidState = world.getFluidState(up);

	Fluid fluid = fluidState.getFluid();

	return fluid == WATER || fluid == FLOWING_WATER;
    }

    @Override
    public void resetTask() {
	this.navigator.clearPath();
    }

    @Override
    protected boolean shouldTeleport(PlayerEntity owner) {
	this.navigator.tryMoveToEntityLiving(owner, this.speed);

	double distance = this.creature.getDistanceSq(owner);

	if (!this.creature.getLeashed() && !this.creature.isPassenger())
	    return !(distance < this.teleportDist * this.teleportDist);

	return false;
    }

    @Override
    public void startExecuting() {
	this.timeToRecalcPath = 0;
    }

}
