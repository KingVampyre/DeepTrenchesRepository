package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PURFUNGA_SAPLING;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.fungus.PurfungaFungusFeature;
import net.minecraft.block.Block;
import net.minecraft.util.Direction.Axis;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class PurfungaTree extends ModThickTree {

	@Override
	public Block getSapingBlock() {
		return PURFUNGA_SAPLING;
	}

	@Override
	protected TemplateTreeFeature getThickTreeFeature(Random random) {
		Axis axis = random.nextBoolean() ? X : Z;

		return new PurfungaFungusFeature(axis, this);
	}

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		return null;
	}

}
