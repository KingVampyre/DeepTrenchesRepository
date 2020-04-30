package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.api.enums.TreeSize.LARGE;
import static github.kingvampire.DeepTrenches.api.enums.TreeSize.MEDIUM;
import static github.kingvampire.DeepTrenches.api.enums.TreeSize.SMALL;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.trees.ModTree;
import github.kingvampire.DeepTrenches.core.world.gen.trees.PlumTreeFeature;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class PlumTree extends ModTree {

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {

		if (random.nextInt(7) == 0)
			return new PlumTreeFeature(LARGE);
		else if (random.nextInt(3) == 0)
			return new PlumTreeFeature(MEDIUM);

		return new PlumTreeFeature(SMALL);
	}

}
