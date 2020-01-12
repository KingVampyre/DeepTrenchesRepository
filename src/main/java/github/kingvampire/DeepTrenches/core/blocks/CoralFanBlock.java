package github.kingvampire.DeepTrenches.core.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CoralFanBlock extends DeadCoralFanBlock {

	private Block deadBlock;

	public CoralFanBlock(Block deadBlock, Properties properties) {
		super(properties);

		this.deadBlock = deadBlock;
	}

	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		this.updateIfDry(state, worldIn, pos);
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		BlockState newState = this.deadBlock.getDefaultState();

		if (!isInWater(state, worldIn, pos))
			newState = newState.with(WATERLOGGED, false);

		worldIn.setBlockState(pos, newState, 2);
	}

}
