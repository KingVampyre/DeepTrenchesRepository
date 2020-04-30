package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_SAPLING;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_SAPLING;

import github.kingvampire.DeepTrenches.api.entity.goals.MoveToRandomBlockGoal;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class FindSaplingGoal extends MoveToRandomBlockGoal {

	public FindSaplingGoal(StaspEntity staspEntity, double speedIn, int range, int height) {
		super(staspEntity, speedIn, range, height, 0);
	}

	@Override
	protected boolean getIsAboveDestination() {
		BlockPos pos = this.creature.getPosition();
		double distance = this.getTargetDistanceSq();

		return this.destinationBlock.withinDistance(pos, distance);
	}

	@Override
	protected int getRunDelay(CreatureEntity creatureIn) {
		return 200;
	}

	@Override
	public boolean shouldContinueExecuting() {
		StaspEntity staspEntity = (StaspEntity) this.creature;

		return staspEntity.getPollen() == 100 && !staspEntity.hasAqueanSap() && super.shouldContinueExecuting();
	}

	@Override
	public boolean shouldExecute() {
		StaspEntity staspEntity = (StaspEntity) this.creature;

		return staspEntity.getPollen() == 100 && !staspEntity.hasAqueanSap() && super.shouldExecute();
	}

	@Override
	protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
		BlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		return block == AQUEAN_SAPLING || block == FUCHSITRA_SAPLING;
	}

}
