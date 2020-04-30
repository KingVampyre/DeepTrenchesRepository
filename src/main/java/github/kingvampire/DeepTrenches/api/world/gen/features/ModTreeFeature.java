package github.kingvampire.DeepTrenches.api.world.gen.features;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;

public class ModTreeFeature extends TreeFeature {

	private int maxHeight;

	public ModTreeFeature(BlockState trunk, BlockState leaf, int minHeight, int maxHeight) {
		this(trunk, leaf, minHeight, maxHeight, false);
	}

	public ModTreeFeature(BlockState trunk, BlockState leaf, int minHeight, int maxHeight, boolean vinesGrowIn) {
		super(NoFeatureConfig::deserialize, true, minHeight, trunk, leaf, vinesGrowIn);

		this.maxHeight = maxHeight;
	}

	@Override
	protected int getHeight(Random random) {
		int bound = this.maxHeight - this.minTreeHeight + 1;

		return this.minTreeHeight + random.nextInt(bound);
	}

}
