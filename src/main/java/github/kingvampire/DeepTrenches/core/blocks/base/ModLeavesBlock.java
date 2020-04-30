package github.kingvampire.DeepTrenches.core.blocks.base;

import static net.minecraft.block.Blocks.OAK_LEAVES;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ModLeavesBlock extends LeavesBlock {

	public ModLeavesBlock() {
		super(Properties.from(OAK_LEAVES));
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return 30;
	}

	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return 60;
	}

}
