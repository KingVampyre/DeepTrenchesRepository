package github.kingvampire.DeepTrenches.api.world.gen.features;

import static net.minecraft.block.Blocks.AIR;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TemplateThickTreeFeature extends TemplateTreeFeature {

	protected ModThickTree thickTree;

	public TemplateThickTreeFeature(BlockState log, ModThickTree thickTree) {
		this(log, thickTree, 0, 0, null);
	}

	public TemplateThickTreeFeature(BlockState log, ModThickTree thickTree, int minHeight, int maxHeight,
			ResourceLocation templateId) {

		super(log, minHeight, maxHeight, templateId);

		this.thickTree = thickTree;
	}

	@Override
	public Set<BlockPos> getSaplings(World world, BlockPos pos) {
		return this.thickTree.getSaplings(world, pos);
	}

	@Override
	public boolean placeTrunk(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {

		for (BlockPos pos : set) {
			worldIn.setBlockState(pos, AIR.getDefaultState(), 4);

			super.placeTrunk(set, worldIn, pos, height);
		}

		return true;
	}

}
