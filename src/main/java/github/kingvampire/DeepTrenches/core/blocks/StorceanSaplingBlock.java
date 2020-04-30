package github.kingvampire.DeepTrenches.core.blocks;

import static net.minecraft.state.properties.SlabType.BOTTOM;
import static net.minecraft.state.properties.SlabType.DOUBLE;
import static net.minecraft.state.properties.SlabType.TOP;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class StorceanSaplingBlock extends SaplingBlock {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_25;
	public static final EnumProperty<SlabType> TYPE = BlockStateProperties.SLAB_TYPE;

	public StorceanSaplingBlock(Tree tree, Properties properties) {
		super(tree, properties);

		this.setDefaultState(this.stateContainer.getBaseState().with(AGE, 0).with(STAGE, 0).with(TYPE, DOUBLE));
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);

		builder.add(AGE);
		builder.add(TYPE);
	}

	@Override
	public void grow(IWorld worldIn, BlockPos pos, BlockState state, Random rand) {
		SlabType type = state.get(TYPE);

		worldIn.setBlockState(pos, state.cycle(AGE), 3);

		if (state.get(STAGE) == 0 && type == DOUBLE) {
			if (state.get(AGE) >= 10) {
				BlockPos up = pos.up();
				BlockState upState = worldIn.getBlockState(up);

				if (upState.canBeReplacedByLeaves(worldIn, up)) {
					worldIn.setBlockState(up, state.with(TYPE, TOP).with(STAGE, 1), 3);
					worldIn.setBlockState(pos, state.with(TYPE, BOTTOM).with(STAGE, 1), 3);
				}
			}
		} else if (state.get(STAGE) == 1) {
			BlockPos blockPos = type == BOTTOM ? pos.up() : pos.down();

			if (!worldIn.isAirBlock(blockPos)) {
				worldIn.setBlockState(blockPos, worldIn.getBlockState(blockPos).cycle(AGE), 3);

				if (state.get(AGE) == 15)
					super.grow(worldIn, type == BOTTOM ? pos : pos.down(), state, rand);
			}
		}
	}

	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {

		if (!this.isValidPosition(state, worldIn, pos))
			worldIn.destroyBlock(pos, false);

		if (!worldIn.isAreaLoaded(pos, 1))
			return; // Forge: prevent loading unloaded chunks when checking neighbor's light

		if (worldIn.getLight(pos.up()) >= 9 && random.nextInt(3) == 0)
			this.grow(worldIn, pos, state, random);

	}

	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing,
			IPlantable plantable) {

		if (state.getBlock() == this && state.get(TYPE) == BOTTOM)
			return true;

		return this.isValidGround(state, world, pos);
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {

		if (state.get(TYPE) == BOTTOM && worldIn.isAirBlock(pos.up()))
			return false;

		return super.isValidPosition(state, worldIn, pos);
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		// TODO Storcean blocks
		return super.isValidGround(state, worldIn, pos);
	}

}
