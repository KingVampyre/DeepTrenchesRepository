package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.api.enums.TreeSize.LARGE;
import static github.kingvampire.DeepTrenches.api.enums.TreeSize.MEDIUM;
import static github.kingvampire.DeepTrenches.api.enums.TreeSize.SMALL;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ANAMEATA_SAPLING;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.fungus.AnameataFungusFeature;
import net.minecraft.block.Block;
import net.minecraft.util.Direction.Axis;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class AnameataTree extends ModThickTree {

	@Override
	public Block getSapingBlock() {
		return ANAMEATA_SAPLING;
	}

	@Override
	protected TemplateTreeFeature getThickTreeFeature(Random random) {
		Axis axis = random.nextBoolean() ? X : Z;

		if (random.nextInt(5) == 0)
			return new AnameataFungusFeature(axis, LARGE, this);
		else if (random.nextInt(3) == 0)
			return new AnameataFungusFeature(axis, MEDIUM, this);

		return new AnameataFungusFeature(axis, SMALL, this);
	}

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		return null;
	}

}
