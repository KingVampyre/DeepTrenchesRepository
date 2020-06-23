package github.kingvampire.DeepTrenches.core.blocks;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.MOSOIL;

import github.kingvampire.DeepTrenches.api.world.gen.trees.ModTree;
import github.kingvampire.DeepTrenches.core.blocks.base.ModSaplingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class StorceanSaplingBlock extends ModSaplingBlock {

    public StorceanSaplingBlock(ModTree tree, Properties properties) {
	super(tree, properties);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
	return state.getBlock() == MOSOIL;
    }

}
