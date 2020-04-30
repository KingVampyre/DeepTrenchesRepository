package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.api.enums.TreeSize.LARGE;
import static github.kingvampire.DeepTrenches.api.enums.TreeSize.MEDIUM;
import static github.kingvampire.DeepTrenches.api.enums.TreeSize.SMALL;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.trees.ModTree;
import github.kingvampire.DeepTrenches.core.world.gen.trees.ThundercloudPlumTreeFeature;
import net.minecraft.util.Direction.Axis;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class ThundercloudPlumTree extends ModTree {

	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		Axis axis = random.nextBoolean() ? X : Z;

		if (random.nextInt(10) == 0)
			return new ThundercloudPlumTreeFeature(axis, LARGE);
		else if (random.nextInt(5) == 0)
			return new ThundercloudPlumTreeFeature(axis, MEDIUM);

		return new ThundercloudPlumTreeFeature(axis, SMALL);
	}

}
