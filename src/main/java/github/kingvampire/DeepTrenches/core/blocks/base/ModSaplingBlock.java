package github.kingvampire.DeepTrenches.core.blocks.base;

import github.kingvampire.DeepTrenches.api.world.gen.trees.ModTree;

public class ModSaplingBlock extends net.minecraft.block.SaplingBlock {

	private final ModTree tree;

	public ModSaplingBlock(ModTree tree, Properties properties) {
		super(tree, properties);

		this.tree = tree;
	}

	public ModTree getTree() {
		return this.tree;
	}

}
