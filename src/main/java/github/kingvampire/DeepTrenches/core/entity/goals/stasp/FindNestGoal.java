package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STASP_NEST;
import static net.minecraft.entity.ai.goal.Goal.Flag.MOVE;

import java.util.EnumSet;

import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import github.kingvampire.DeepTrenches.core.entity.StaspNestTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class FindNestGoal extends MoveToBlockGoal {

	private int range;

	public FindNestGoal(StaspEntity staspEntity, double speed, int range, int height) {
		super(staspEntity, speed, range, height);

		this.range = range;

		this.setMutexFlags(EnumSet.of(MOVE));
	}

	protected void func_220725_g() {
		int x = this.destinationBlock.getX();
		int y = this.destinationBlock.getY();
		int z = this.destinationBlock.getZ();

		this.creature.getNavigator().tryMoveToXYZ(x, y, z, this.movementSpeed);
	}

	@Override
	protected int getRunDelay(CreatureEntity creatureIn) {
		return 60 + creatureIn.getRNG().nextInt(60);
	}

	@Override
	public boolean shouldContinueExecuting() {
		StaspEntity staspEntity = (StaspEntity) this.creature;

		return !staspEntity.isAngry() && super.shouldContinueExecuting();
	}

	@Override
	public boolean shouldExecute() {
		StaspEntity staspEntity = (StaspEntity) this.creature;

		int ticks = staspEntity.getTicksSincePollination();
		int aqueanSap = staspEntity.getAqueanSap();

		if (!staspEntity.isAngry() && aqueanSap == 100)
			return ticks >= 100 && super.shouldExecute();

		return false;
	}

	@Override
	protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
		BlockState state = worldIn.getBlockState(pos);
		TileEntity tileEntity = worldIn.getTileEntity(pos);

		if (state.getBlock() == STASP_NEST) {
			StaspNestTileEntity staspNest = (StaspNestTileEntity) tileEntity;

			if (staspNest.canAddStasp())
				return true;
		}

		return false;
	}

	@Override
	public void startExecuting() {
		super.startExecuting();

		this.creature.setHomePosAndDistance(this.destinationBlock, this.range);
	}

}