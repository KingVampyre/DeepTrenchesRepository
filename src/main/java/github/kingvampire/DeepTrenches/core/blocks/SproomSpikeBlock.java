package github.kingvampire.DeepTrenches.core.blocks;

import static github.kingvampire.DeepTrenches.core.init.DamageSources.SPROOM_SPIKE;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SPROOM_LOG;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SPROOM_WOOD;
import static net.minecraft.state.properties.BlockStateProperties.FACING;
import static net.minecraft.state.properties.BlockStateProperties.HALF;
import static net.minecraft.state.properties.Half.BOTTOM;
import static net.minecraft.state.properties.Half.TOP;
import static net.minecraft.util.Direction.NORTH;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class SproomSpikeBlock extends BushBlock {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_25;

	public SproomSpikeBlock(Properties properties) {
		super(properties);

		this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0).with(FACING, NORTH).with(HALF, TOP));
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(AGE, FACING, HALF);
	}

	private Direction getFacing(IWorld worldIn, BlockPos currentPos, BlockState stateIn) {
		Direction direction = stateIn.get(FACING);
		Direction opposite = direction.getOpposite();
		BlockPos offset = currentPos.offset(opposite);

		if (worldIn.isAirBlock(offset))
			for (Direction dir : Direction.values()) {
				BlockPos pos = currentPos.offset(dir);
				BlockState state = worldIn.getBlockState(pos);

				if (this.isValidWood(state))
					return dir.getOpposite();

				if (dir.getAxis().isHorizontal() && state.getBlock() == this && dir != state.get(FACING))
					return dir;
			}

		return direction;
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

		switch (state.get(FACING).getAxis()) {
		case X:
		default:
			return Block.makeCuboidShape(0, 6, 6, 16, 10, 10);
		case Z:
			return Block.makeCuboidShape(6, 6, 0, 10, 10, 16);
		case Y:
			return Block.makeCuboidShape(6, 0, 6, 10, 16, 10);
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState state = super.getStateForPlacement(context);

		BlockPos pos = context.getPos();
		World world = context.getWorld();

		for (Direction direction : context.getNearestLookingDirections()) {
			state = state.with(FACING, direction.getOpposite());

			if (state.isValidPosition(world, pos))
				return state;
		}

		return null;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		Direction direction = state.get(FACING).getOpposite();

		int i = 1;

		while (worldIn.getBlockState(pos.offset(direction, i)).getBlock() == this)
			i++;

		BlockState stateIn = worldIn.getBlockState(pos.offset(direction, i));

		return 1 < 5 && this.isValidWood(stateIn);
	}

	private boolean isValidWood(BlockState state) {
		return state.getBlock() == SPROOM_LOG || state.getBlock() == SPROOM_WOOD;
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {

		if (state.get(HALF) == TOP)
			entityIn.attackEntityFrom(SPROOM_SPIKE, 1);
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		Direction direction = state.get(FACING).getOpposite();
		BlockPos offset = pos.offset(direction);

		if (!state.isValidPosition(worldIn, pos))
			worldIn.destroyBlock(pos, true);
		else if (worldIn.isAirBlock(offset) && this.isValidPosition(state, worldIn, offset)) {
			if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, true)) {
				if (state.get(AGE) == 25) {
					worldIn.setBlockState(offset, state.cycle(AGE));
					worldIn.setBlockState(pos, state.cycle(AGE).with(HALF, BOTTOM), 4);
				} else
					worldIn.setBlockState(pos, state.cycle(AGE), 4);

				ForgeHooks.onCropsGrowPost(worldIn, pos, state);
			}
		}
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {

		Direction dir = this.getFacing(worldIn, currentPos, stateIn);
		Direction direction = stateIn.get(FACING);

		BlockPos offset = currentPos.offset(direction);
		BlockState state = worldIn.getBlockState(offset);

		if (direction != dir)
			return stateIn.with(FACING, dir.getOpposite());

		if (!stateIn.isValidPosition(worldIn, currentPos))
			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);

		if (state.getBlock() == this && stateIn.get(HALF) == TOP)
			return stateIn.with(HALF, BOTTOM);

		if (stateIn.get(HALF) == BOTTOM && state.isAir(worldIn, offset))
			return stateIn.with(HALF, TOP);

		return stateIn;
	}

}
