package github.kingvampire.DeepTrenches.api.world.gen.trees;

import static net.minecraft.block.SaplingBlock.STAGE;

import java.util.Random;
import java.util.Set;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public abstract class StorceanThickTree extends ModThickTree {

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		return null;
	}

	@Override
	public boolean canPlaceTree(IWorld worldIn, Set<BlockPos> set) {

		if (set.isEmpty() || set.size() <= 1)
			return false;

		return set.stream().map(worldIn::getBlockState).map(state -> state.get(STAGE)).allMatch(stage -> stage == 1);
	}

}
