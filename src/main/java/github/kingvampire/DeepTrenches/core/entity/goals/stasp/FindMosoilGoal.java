package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class FindMosoilGoal extends MoveToRandomBlockGoal {

    public FindMosoilGoal(StaspEntity staspEntity, double speedIn, int range, int height) {
	super(staspEntity, speedIn, range, height, 25);
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

	// TODO mosoil
	return block == Blocks.GRASS_BLOCK;
    }

}
