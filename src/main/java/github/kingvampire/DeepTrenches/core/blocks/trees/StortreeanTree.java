package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STORTREEAN_SAPLING;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.StorceanThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.trees.thick.StortreeanTreeFeature;
import net.minecraft.block.Block;
import net.minecraft.util.Direction.Axis;

public class StortreeanTree extends StorceanThickTree {

	@Override
	public Block getSapingBlock() {
		return STORTREEAN_SAPLING;
	}

	@Override
	protected TemplateTreeFeature getThickTreeFeature(Random random) {
		Axis axis = random.nextBoolean() ? X : Z;

		return new StortreeanTreeFeature(this, axis);
	}

}
