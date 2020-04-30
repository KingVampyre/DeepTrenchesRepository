package github.kingvampire.DeepTrenches.core.blocks.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SPROOM_SAPLING;

import java.util.Random;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.StorceanThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.trees.thick.SproomTreeFeature;
import net.minecraft.block.Block;

public class SproomTree extends StorceanThickTree {

	@Override
	public Block getSapingBlock() {
		return SPROOM_SAPLING;
	}

	@Override
	protected TemplateTreeFeature getThickTreeFeature(Random random) {
		return new SproomTreeFeature(this);
	}

}
