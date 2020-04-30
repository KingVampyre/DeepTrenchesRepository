package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CHERRY_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CHERRY_LOG;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CHERRY_SAPLING;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.features.ModTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.trees.CherryTreeFeature;
import github.kingvampire.DeepTrenches.core.world.gen.trees.big.BigCherryTreeFeature;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class CherryTree extends ModThickTree {

	@Override
	public Block getSapingBlock() {
		return CHERRY_SAPLING;
	}

	@Override
	protected TemplateTreeFeature getThickTreeFeature(Random random) {
		return new BigCherryTreeFeature(this);
	}

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		BlockState leaves = CHERRY_LEAVES.getDefaultState();
		BlockState log = CHERRY_LOG.getDefaultState();

		if (random.nextInt(5) == 0)
			return new CherryTreeFeature();

		return new ModTreeFeature(log, leaves, 7, 10);
	}

}
