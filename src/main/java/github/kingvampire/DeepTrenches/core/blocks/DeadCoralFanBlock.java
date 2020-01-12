package github.kingvampire.DeepTrenches.core.blocks;

import static net.minecraft.block.Blocks.AIR;
import static net.minecraft.state.properties.BlockStateProperties.FACING_EXCEPT_UP;
import static net.minecraft.util.Direction.UP;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractCoralPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class DeadCoralFanBlock extends AbstractCoralPlantBlock {

	public DeadCoralFanBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);

		builder.add(FACING_EXCEPT_UP);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

		switch (state.get(FACING_EXCEPT_UP)) {
		case EAST:
			return Block.makeCuboidShape(0.0D, 4.0D, 0.0D, 11.0D, 12.0D, 16.0D);
		case NORTH:
			return Block.makeCuboidShape(0.0D, 4.0D, 5.0D, 16.0D, 12.0D, 16.0D);
		case SOUTH:
			return Block.makeCuboidShape(0.0D, 4.0D, 0.0D, 16.0D, 12.0D, 11.0D);
		case DOWN:
			return Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);
		case WEST:
			return Block.makeCuboidShape(5.0D, 4.0D, 0.0D, 16.0D, 12.0D, 16.0D);
		default:
			return null;
		}
	}

	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getPos();

		BlockState state = super.getStateForPlacement(context);
		BlockState under = world.getBlockState(pos.down());

		for (Direction direction : context.getNearestLookingDirections()) {

			if (direction.getAxis().isHorizontal()) {
				state = state.with(FACING_EXCEPT_UP, direction.getOpposite());

				if (state.isValidPosition(context.getWorld(), context.getPos()))
					return state;
			}

			if (direction == UP && under.getBlock() != this.getBlock())
				return state.with(FACING_EXCEPT_UP, direction.getOpposite()).with(WATERLOGGED, state.getBlock() != AIR);
		}

		return AIR.getDefaultState();
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		Direction direction = state.get(FACING_EXCEPT_UP);
		BlockPos blockpos = pos.offset(direction.getOpposite());

		return Block.hasSolidSide(worldIn.getBlockState(blockpos), worldIn, blockpos, direction);
	}

}