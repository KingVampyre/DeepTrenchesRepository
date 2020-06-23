package github.kingvampire.DeepTrenches.core.blocks;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.MOSOIL;

import github.kingvampire.DeepTrenches.core.blocks.base.ModTallGrassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ReebloonBlock extends ModTallGrassBlock {

    public ReebloonBlock(Properties properties) {
	super(properties);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
	return state.getBlock() == MOSOIL;
    }

}
