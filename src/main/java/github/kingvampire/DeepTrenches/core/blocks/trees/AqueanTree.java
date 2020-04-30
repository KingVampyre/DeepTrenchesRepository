package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.api.enums.TreeSize.LARGE;
import static github.kingvampire.DeepTrenches.api.enums.TreeSize.MEDIUM;
import static github.kingvampire.DeepTrenches.api.enums.TreeSize.SMALL;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.trees.ModTree;
import github.kingvampire.DeepTrenches.core.world.gen.trees.AqueanTreeFeature;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class AqueanTree extends ModTree {

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {

		if (random.nextInt(7) == 0)
			return new AqueanTreeFeature(LARGE);
		if (random.nextInt(3) == 0)
			return new AqueanTreeFeature(MEDIUM);

		return new AqueanTreeFeature(SMALL);
	}

}
