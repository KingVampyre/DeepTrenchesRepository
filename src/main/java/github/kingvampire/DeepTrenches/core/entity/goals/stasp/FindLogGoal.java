package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.api.capabilities.anger.AngerProvider.ANGER_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_LOG;
import static net.minecraft.util.math.BlockPos.ZERO;

import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.util.LazyOptional;

public class FindLogGoal extends MoveToRandomBlockGoal {

    public FindLogGoal(StaspEntity staspEntity, double speedIn, int range, int height) {
	super(staspEntity, speedIn, range, height, 10);
    }

    @Override
    protected void func_220725_g() {
	double x = this.destinationBlock.getX();
	double y = this.destinationBlock.getY();
	double z = this.destinationBlock.getZ();

	this.creature.getNavigator().tryMoveToXYZ(x + 0.5, y, z + 0.5, this.movementSpeed);
    }

    @Override
    protected boolean getIsAboveDestination() {
	BlockPos pos = this.creature.getPosition();
	double distance = this.getTargetDistanceSq();

	return this.destinationBlock.withinDistance(pos, distance);
    }

    @Override
    protected int getRunDelay(CreatureEntity creatureIn) {
	return 1200;
    }

    @Override
    public boolean shouldContinueExecuting() {
	StaspEntity staspEntity = (StaspEntity) this.creature;

	return staspEntity.getAqueanSap() < 100 && !staspEntity.hasPollen() && super.shouldContinueExecuting();
    }

    @Override
    public boolean shouldExecute() {
	LazyOptional<IAnger> anger = this.creature.getCapability(ANGER_CAPABILITY);
	StaspEntity staspEntity = (StaspEntity) this.creature;

	if (anger.isPresent()) {
	    IAnger ianger = anger.orElseThrow(IllegalArgumentException::new);

	    if (staspEntity.getAqueanSap() < 100 && !staspEntity.hasPollen() && !ianger.isAngry()) {

		if (this.destinationBlock == ZERO)
		    return this.searchForDestination();

		if (this.runDelay > 0) {
		    --this.runDelay;

		    return false;
		}

		return this.searchForDestination();
	    }
	}

	return false;
    }

    @Override
    protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
	BlockState state = worldIn.getBlockState(pos);
	Block block = state.getBlock();

	return block == AQUEAN_LOG;
    }

}
