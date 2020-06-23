package github.kingvampire.DeepTrenches.core.blocks;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.REEBLOON;

import java.util.Random;

import github.kingvampire.DeepTrenches.core.blocks.base.ModGrassBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MosoilBlock extends ModGrassBlock {

    public static final BooleanProperty STORCEAN = BooleanProperty.create("storcean");

    public MosoilBlock(Block block, Properties properties) {
	super(block, properties);
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
	BlockState reebloon = REEBLOON.getDefaultState();
	BlockPos up = pos.up();

	for (int i = 0; i < 128; ++i) {
	    BlockPos currPos = up;
	    int j = 0;

	    while (true) {
		if (j >= i / 16) {

		    BlockState stateIn = worldIn.getBlockState(currPos);

		    if (stateIn.getBlock() == reebloon.getBlock() && rand.nextInt(10) == 0) {
			IGrowable growable = (IGrowable) reebloon.getBlock();

			growable.grow(worldIn, rand, currPos, stateIn);
		    }

		    if (!worldIn.isAirBlock(currPos))
			break;

		    if (reebloon.isValidPosition(worldIn, currPos))
			worldIn.setBlockState(currPos, reebloon, 3);

		    break;
		}

		currPos = currPos.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
		BlockState ground = worldIn.getBlockState(currPos.down());

		if (ground.getBlock() != this || worldIn.getBlockState(currPos).func_224756_o(worldIn, currPos))
		    break;

		++j;
	    }
	}
    }

    @Override
    protected void fillStateContainer(Builder<Block, BlockState> builder) {
	super.fillStateContainer(builder);

	builder.add(STORCEAN);
    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
	super.tick(state, worldIn, pos, random);

	if (!worldIn.isRemote()) {

	    if (state.get(SNOWY)) {
		// TODO check dimension
	    }

	}

    }

}
