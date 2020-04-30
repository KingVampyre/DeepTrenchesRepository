package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STROOMEAN_SAPLING;
import static net.minecraft.util.Direction.Plane.HORIZONTAL;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.fungus.StroomeanFungusFeature;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class StroomeanTree extends ModThickTree {

	@Override
	public Block getSapingBlock() {
		return STROOMEAN_SAPLING;
	}

	@Override
	protected TemplateTreeFeature getThickTreeFeature(Random random) {
		Direction direction = HORIZONTAL.random(random);

		return new StroomeanFungusFeature(direction, this, true);
	}

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		return null;
	}

}
