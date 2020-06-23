package github.kingvampire.DeepTrenches.core.blocks.base;

import static net.minecraft.block.Blocks.AIR;
import static net.minecraft.block.Blocks.WATER;
import static net.minecraft.state.properties.BlockStateProperties.FACING_EXCEPT_UP;
import static net.minecraft.util.Direction.DOWN;

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
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class ModDeadCoralFanBlock extends AbstractCoralPlantBlock {

    public ModDeadCoralFanBlock(Properties properties) {
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
	    return Block.makeCuboidShape(0, 4, 0, 11, 12, 16);
	case NORTH:
	    return Block.makeCuboidShape(0, 4, 5, 16, 12, 16);
	case SOUTH:
	    return Block.makeCuboidShape(0, 4, 0, 16, 12, 11);
	case DOWN:
	    return Block.makeCuboidShape(2, 0, 2, 14, 4, 14);
	case WEST:
	    return Block.makeCuboidShape(5, 4, 0, 16, 12, 16);
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

		if (state.isValidPosition(world, pos))
		    return state;
	    }

	    if (direction == DOWN && under.getBlock() != this.getBlock()) {
		state = state.with(FACING_EXCEPT_UP, DOWN);

		if (state.isValidPosition(world, pos) && under.getBlock() != AIR && under.getBlock() != WATER)
		    return state;
	    }
	}

	return null;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
	Direction direction = state.get(FACING_EXCEPT_UP);
	BlockPos offset = pos.offset(direction.getOpposite());

	if (direction == DOWN)
	    return super.isValidPosition(state, worldIn, pos);

	return Block.hasSolidSide(worldIn.getBlockState(offset), worldIn, offset, direction);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
	    BlockPos currentPos, BlockPos facingPos) {

	Direction direction = stateIn.get(FACING_EXCEPT_UP);

	if (facing.getOpposite() == direction)
	    return !stateIn.isValidPosition(worldIn, currentPos) ? AIR.getDefaultState() : stateIn;

	return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

}