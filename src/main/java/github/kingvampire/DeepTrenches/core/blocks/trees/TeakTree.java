package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.api.enums.TreeSize.LARGE;
import static github.kingvampire.DeepTrenches.api.enums.TreeSize.MEDIUM;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.TEAK_SAPLING;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.trees.TeakTreeFeature;
import github.kingvampire.DeepTrenches.core.world.gen.trees.thick.TeakThickTreeFeature;
import net.minecraft.block.Block;
import net.minecraft.util.Direction.Axis;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class TeakTree extends ModThickTree {

	@Override
	public Block getSapingBlock() {
		return TEAK_SAPLING;
	}

	@Override
	protected TemplateTreeFeature getThickTreeFeature(Random random) {
		Axis axis = random.nextBoolean() ? X : Z;

		if (random.nextInt(10) == 0)
			return new TeakThickTreeFeature(this, axis, LARGE);

		return new TeakThickTreeFeature(this, axis, MEDIUM);
	}

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		return new TeakTreeFeature();
	}

}
