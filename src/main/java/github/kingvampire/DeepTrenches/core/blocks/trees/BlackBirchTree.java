package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BLACK_BIRCH_SAPLING;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.trees.BlackBirchTreeFeature;
import github.kingvampire.DeepTrenches.core.world.gen.trees.thick.BlackBirchThickTreeFeature;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class BlackBirchTree extends ModThickTree {

	@Override
	public Block getSapingBlock() {
		return BLACK_BIRCH_SAPLING;
	}

	@Override
	protected TemplateTreeFeature getThickTreeFeature(Random random) {
		return new BlackBirchThickTreeFeature(this);
	}

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		return new BlackBirchTreeFeature();
	}

}
