package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.MOSOIL;

import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class FindFlowersGoal extends MoveToRandomBlockGoal {

    public FindFlowersGoal(StaspEntity staspEntity, double speed, int range, int height) {
	super(staspEntity, speed, range, height, 10);
    }

    @Override
    protected int getRunDelay(CreatureEntity creatureIn) {
	return 600;
    }

    @Override
    public boolean shouldContinueExecuting() {
	StaspEntity staspEntity = (StaspEntity) this.creature;

	return staspEntity.getPollen() < 100 && !staspEntity.hasAqueanSap() && super.shouldContinueExecuting();
    }

    @Override
    public boolean shouldExecute() {
	StaspEntity staspEntity = (StaspEntity) this.creature;

	return staspEntity.getPollen() < 100 && !staspEntity.hasAqueanSap() && super.shouldExecute();
    }

    @Override
    protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
	BlockState state = worldIn.getBlockState(pos);
	Block block = state.getBlock();

	BlockPos down = this.creature.getPosition().down();
	BlockPos up = pos.up();

	BlockState upState = worldIn.getBlockState(up);

	if (!down.equals(pos) && upState.isAir(worldIn, up))
	    return block == AQUEAN_LEAVES || block == FUCHSITRA_LEAVES || block == MOSOIL;

	return false;
    }

}