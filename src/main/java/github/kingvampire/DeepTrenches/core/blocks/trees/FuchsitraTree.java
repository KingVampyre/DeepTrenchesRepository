package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_SAPLING;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.trees.FuchsitraTreeFeature;
import github.kingvampire.DeepTrenches.core.world.gen.trees.big.BigFuchsitraTreeFeature;
import github.kingvampire.DeepTrenches.core.world.gen.trees.thick.FuchsitraThickTreeFeature;
import net.minecraft.block.Block;
import net.minecraft.util.Direction.Axis;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class FuchsitraTree extends ModThickTree {

	@Override
	public Block getSapingBlock() {
		return FUCHSITRA_SAPLING;
	}

	@Override
	protected TemplateTreeFeature getThickTreeFeature(Random random) {
		Axis axis = random.nextBoolean() ? X : Z;

		if (random.nextInt(7) == 0)
			return new FuchsitraThickTreeFeature(this, axis);

		return new BigFuchsitraTreeFeature(this, axis);
	}

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		Axis axis = random.nextBoolean() ? X : Z;

		return new FuchsitraTreeFeature(axis);
	}

}
