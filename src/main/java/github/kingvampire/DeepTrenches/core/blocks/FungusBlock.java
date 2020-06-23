package github.kingvampire.DeepTrenches.core.blocks;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.MOSOIL;
import static net.minecraft.block.Blocks.AIR;
import static net.minecraft.block.Blocks.SPONGE;
import static net.minecraft.block.Blocks.WET_SPONGE;
import static net.minecraft.fluid.Fluids.EMPTY;
import static net.minecraft.fluid.Fluids.FLOWING_WATER;
import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.trees.ModTree;
import github.kingvampire.DeepTrenches.core.blocks.base.ModSaplingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class FungusBlock extends ModSaplingBlock {

    private boolean canGrowInLand;

    public FungusBlock(ModTree tree, Properties properties, boolean canGrowInLand) {
	super(tree, properties);

	this.canGrowInLand = canGrowInLand;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	super.fillStateContainer(builder);

	builder.add(WATERLOGGED);
    }

    @SuppressWarnings("deprecation")
    public IFluidState getFluidState(BlockState state) {
	return state.get(WATERLOGGED) ? WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {

	if (!state.get(WATERLOGGED) && !this.canGrowInLand)
	    return false;

	IFluidState ifluidstate = worldIn.getFluidState(pos);
	Fluid fluid = ifluidstate.getFluid();

	if (fluid == WATER)
	    return super.isValidPosition(state, worldIn, pos);
	else if (fluid == EMPTY)
	    return this.canGrowInLand ? super.isValidPosition(state, worldIn, pos) : false;

	return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
	IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());

	return super.getStateForPlacement(context).with(WATERLOGGED, ifluidstate.getFluid() == WATER);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
	return state.getBlock() == MOSOIL;
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {

	if (!this.isValidPosition(state, worldIn, pos))
	    worldIn.setBlockState(pos, AIR.getDefaultState());

	super.tick(state, worldIn, pos, random);
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
	    BlockPos currentPos, BlockPos facingPos) {

	Block block = facingState.getBlock();

	if (stateIn.get(WATERLOGGED) && block == SPONGE || block == WET_SPONGE) {
	    worldIn.setBlockState(currentPos, stateIn.with(WATERLOGGED, false), 4);

	    if (!this.canGrowInLand)
		return Blocks.AIR.getDefaultState();
	    else
		return stateIn.with(WATERLOGGED, false);
	}

	IFluidState fluidState = worldIn.getFluidState(facingPos);
	BlockState state = worldIn.getBlockState(currentPos);

	if (fluidState.getFluid() == FLOWING_WATER)
	    return state;

	if (fluidState.getFluid() == WATER)
	    return state.with(WATERLOGGED, true);

	return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

}
