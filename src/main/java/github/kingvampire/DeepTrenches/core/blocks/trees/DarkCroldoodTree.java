package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.DARK_CROLOOD_SAPLING;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.trees.thick.DarkCroloodTreeFeature;
import net.minecraft.block.Block;
import net.minecraft.util.Direction.Axis;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class DarkCroldoodTree extends ModThickTree {

	@Override
	public Block getSapingBlock() {
		return DARK_CROLOOD_SAPLING;
	}

	@Override
	protected TemplateTreeFeature getThickTreeFeature(Random random) {
		Axis axis = random.nextBoolean() ? X : Z;

		return new DarkCroloodTreeFeature(axis, this, false);
	}

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		return null;
	}

}
