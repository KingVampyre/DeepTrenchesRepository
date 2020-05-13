package github.kingvampire.DeepTrenches.core.entity.goals.betta;

import static net.minecraft.fluid.Fluids.FLOWING_WATER;
import static net.minecraft.fluid.Fluids.WATER;

import github.kingvampire.DeepTrenches.api.entity.goals.FollowOwnerGoal;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BettaFollowOwnerGoal extends FollowOwnerGoal {

    public BettaFollowOwnerGoal(CreatureEntity creature, double speed, float minDist, float maxDist,
	    float teleportDist) {

	super(creature, speed, minDist, maxDist, teleportDist);
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
    public void startExecuting() {
	this.timeToRecalcPath = 0;
    }

}
