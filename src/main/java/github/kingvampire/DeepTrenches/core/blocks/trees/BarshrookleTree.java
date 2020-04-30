package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BARSHROOKLE_SAPLING;
import static net.minecraft.util.Direction.Plane.HORIZONTAL;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.fungus.BarshrookleFungusFeature;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class BarshrookleTree extends ModThickTree {

	@Override
	public Block getSapingBlock() {
		return BARSHROOKLE_SAPLING;
	}

	@Override
	protected TemplateTreeFeature getThickTreeFeature(Random random) {
		return null;
	}

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		Direction direction = HORIZONTAL.random(random);

		return new BarshrookleFungusFeature(direction, this, true);
	}

}
