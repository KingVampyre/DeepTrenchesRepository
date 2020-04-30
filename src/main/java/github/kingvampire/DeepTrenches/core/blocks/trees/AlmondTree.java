package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ALMOND_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ALMOND_LOG;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ALMOND_SAPLING;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.features.ModTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.trees.AlmondTreeFeature;
import github.kingvampire.DeepTrenches.core.world.gen.trees.big.BigAlmondTreeFeature;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class AlmondTree extends ModThickTree {

	@Override
	public Block getSapingBlock() {
		return ALMOND_SAPLING;
	}

	@Override
	protected TemplateTreeFeature getThickTreeFeature(Random random) {
		return new BigAlmondTreeFeature(this);
	}

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		BlockState leaves = ALMOND_LEAVES.getDefaultState();
		BlockState log = ALMOND_LOG.getDefaultState();

		if (random.nextInt(5) == 0)
			return new AlmondTreeFeature();

		return new ModTreeFeature(log, leaves, 4, 10);
	}

}
