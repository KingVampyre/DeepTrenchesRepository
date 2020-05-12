package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static net.minecraft.block.SaplingBlock.STAGE;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.trees.ModTree;
import github.kingvampire.DeepTrenches.core.blocks.base.ModSaplingBlock;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PollinateSaplingGoal extends MoveToBlockThenExecuteGoal {

    public PollinateSaplingGoal(StaspEntity staspEntity, MoveToRandomBlockGoal findBlockGoal) {
	super(staspEntity, findBlockGoal);
    }

    @Override
    public boolean shouldExecute() {
	int pollen = this.staspEntity.getPollen();

	return !this.staspEntity.hasAqueanSap() && pollen == 100 && super.shouldExecute();
    }

    @Override
    protected int getRunDelay(Random rand) {
	return 0;
    }

    @Override
    protected void onFinished() {
	BlockPos pos = this.findBlockGoal.getDestination();
	World world = this.staspEntity.getEntityWorld();

	BlockState state = world.getBlockState(pos);
	Block block = state.getBlock();

	if (block instanceof ModSaplingBlock) {
	    BlockState blockUnder = world.getBlockState(pos.down());

	    ModSaplingBlock sapling = (ModSaplingBlock) block;
	    ModTree tree = sapling.getTree();

	    if (state.get(STAGE) == 0)
		world.setBlockState(pos, state.cycle(STAGE), 3);
	    else
		tree.spawn(world, pos, blockUnder, world.rand);

	    this.staspEntity.setPollen(0);
	}
    }

}
