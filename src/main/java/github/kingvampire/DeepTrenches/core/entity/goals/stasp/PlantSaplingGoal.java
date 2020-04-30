package github.kingvampire.DeepTrenches.core.entity.goals.stasp;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_SAPLING;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_SAPLING;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.entity.goals.MoveToBlockThenExecuteGoal;
import github.kingvampire.DeepTrenches.api.entity.goals.MoveToRandomBlockGoal;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlantSaplingGoal extends MoveToBlockThenExecuteGoal {

	public PlantSaplingGoal(StaspEntity staspEntity, MoveToRandomBlockGoal findBlockGoal) {
		super(staspEntity, findBlockGoal);
	}

	@Override
	public boolean shouldExecute() {
		return this.staspEntity.getPollen() == 100 && super.shouldExecute();
	}

	@Override
	protected int getRunDelay(Random rand) {
		return 0;
	}

	@Override
	protected void onFinished() {
		Random random = this.staspEntity.getRNG();

		BlockPos pos = this.findBlockGoal.getDestination().up();
		World worldIn = this.staspEntity.getEntityWorld();

		Block block = random.nextBoolean() ? AQUEAN_SAPLING : FUCHSITRA_SAPLING;
		BlockState state = block.getDefaultState();

		if (worldIn.setBlockState(pos, state))
			this.staspEntity.setPollen(0);
	}

}
