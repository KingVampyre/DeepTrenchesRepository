package github.kingvampire.DeepTrenches.core.blocks;

import static net.minecraft.block.Blocks.AIR;
import static net.minecraft.block.DoublePlantBlock.HALF;
import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.state.properties.DoubleBlockHalf.LOWER;
import static net.minecraft.state.properties.DoubleBlockHalf.UPPER;

import javax.annotation.Nullable;

import github.kingvampire.DeepTrenches.core.blocks.base.ModDeadCoralPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class DeadDoubleCoralBlock extends ModDeadCoralPlantBlock {

	public DeadDoubleCoralBlock(Properties properties) {
		super(properties);

		this.setDefaultState(this.stateContainer.getBaseState().with(HALF, LOWER).with(WATERLOGGED, true));
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);

		builder.add(HALF);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState state = context.getWorld().getBlockState(context.getPos().up());

		return state.isReplaceable(context) ? super.getStateForPlacement(context) : AIR.getDefaultState();
	}

	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState down = worldIn.getBlockState(pos.down());
		int height = worldIn.getDimension().getHeight() - 1;

		if (pos.getY() < height)
			if (state.get(HALF) == LOWER)
				return down.getBlock() != this;
			else
				return down.getBlock() == this;

		return false;
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {

		if (stateIn.get(WATERLOGGED))
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, WATER, WATER.getTickRate(worldIn));

		return stateIn;
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		BlockPos up = pos.up();
		BlockState stateIn = worldIn.getBlockState(up);

		worldIn.setBlockState(up, state.with(HALF, UPPER).with(WATERLOGGED, stateIn.getBlock() == Blocks.WATER));
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockPos half = state.get(HALF) == LOWER ? pos.up() : pos.down();

		worldIn.setBlockState(half, AIR.getDefaultState(), 2);
		super.onBlockHarvested(worldIn, pos, state, player);
	}

}
