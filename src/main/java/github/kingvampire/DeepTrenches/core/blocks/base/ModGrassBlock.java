package github.kingvampire.DeepTrenches.core.blocks.base;

import static net.minecraft.block.Blocks.SNOW;
import static net.minecraft.block.SnowBlock.LAYERS;
import static net.minecraft.tags.FluidTags.WATER;
import static net.minecraft.util.Direction.UP;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;

public class ModGrassBlock extends GrassBlock {

    private Block block;

    public ModGrassBlock(Block block, Properties properties) {
	super(properties);
    }

    public boolean func_220256_c(BlockState stateIn, IWorldReader worldIn, BlockPos pos) {
	BlockPos up = pos.up();

	return func_220257_b(stateIn, worldIn, pos) && !worldIn.getFluidState(up).isTagged(WATER);
    }

    public boolean func_220257_b(BlockState stateIn, IWorldReader worldIn, BlockPos pos) {
	BlockPos up = pos.up();
	BlockState state = worldIn.getBlockState(up);

	if (state.getBlock() == SNOW && state.get(LAYERS) == 1)
	    return true;

	int opacity = state.getOpacity(worldIn, up);
	int light = LightEngine.func_215613_a(worldIn, stateIn, pos, state, up, UP, opacity);

	return light < worldIn.getMaxLightLevel();
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {

	if (!worldIn.isRemote()) {

	    if (!worldIn.isAreaLoaded(pos, 3))
		return;

	    if (!func_220257_b(state, worldIn, pos))
		worldIn.setBlockState(pos, this.block.getDefaultState());
	    else {

		if (worldIn.getLight(pos.up()) >= 9) {
		    BlockState stateIn = this.getDefaultState();

		    for (int i = 0; i < 4; ++i) {
			BlockPos spread = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);

			BlockState spreadState = worldIn.getBlockState(spread);
			BlockState upState = worldIn.getBlockState(spread.up());

			if (spreadState.getBlock() == this.block && func_220256_c(stateIn, worldIn, spread))
			    worldIn.setBlockState(spread, stateIn.with(SNOWY, upState.getBlock() == SNOW));

		    }
		}

	    }
	}
    }

}
