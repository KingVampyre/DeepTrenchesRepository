package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PIN_CHERRY_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PIN_CHERRY_LOG;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PIN_CHERRY_SAPLING;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.features.ModTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.trees.PinCherryTreeFeature;
import github.kingvampire.DeepTrenches.core.world.gen.trees.big.BigPinCherryTreeFeature;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class PinCherryTree extends ModThickTree {

	@Override
	public Block getSapingBlock() {
		return PIN_CHERRY_SAPLING;
	}

	@Override
	protected TemplateTreeFeature getThickTreeFeature(Random random) {
		return new BigPinCherryTreeFeature(this);
	}

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		BlockState leaves = PIN_CHERRY_LEAVES.getDefaultState();
		BlockState log = PIN_CHERRY_LOG.getDefaultState();

		if (random.nextInt(5) == 0)
			return new PinCherryTreeFeature();

		return new ModTreeFeature(log, leaves, 4, 10);
	}

}
