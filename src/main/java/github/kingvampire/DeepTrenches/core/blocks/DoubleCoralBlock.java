package github.kingvampire.DeepTrenches.core.blocks;

import static net.minecraft.block.DoublePlantBlock.HALF;
import static net.minecraft.state.properties.DoubleBlockHalf.LOWER;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DoubleCoralBlock extends DeadDoubleCoralBlock {

	private Block deadBlock;

	public DoubleCoralBlock(Block deadBlock, Properties properties) {
		super(properties);

		this.deadBlock = deadBlock;
	}

	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		this.updateIfDry(state, worldIn, pos);
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		BlockState deadBlock = this.deadBlock.getDefaultState();

		BlockState newState = deadBlock.with(HALF, state.get(HALF));
		BlockPos blockPos = state.get(HALF) == LOWER ? pos.up() : pos.down();
		BlockState halfState = worldIn.getBlockState(blockPos);

		if (!isInWater(state, worldIn, pos))
			newState = newState.with(WATERLOGGED, false);

		halfState = deadBlock.with(HALF, halfState.get(HALF)).with(WATERLOGGED, halfState.get(WATERLOGGED));

		worldIn.setBlockState(blockPos, halfState, 2);
		worldIn.setBlockState(pos, newState, 2);
	}

}
