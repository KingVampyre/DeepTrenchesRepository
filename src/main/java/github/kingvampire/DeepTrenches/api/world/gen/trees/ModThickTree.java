package github.kingvampire.DeepTrenches.api.world.gen.trees;

import static net.minecraft.block.Blocks.AIR;
import static net.minecraft.util.Direction.Plane.HORIZONTAL;
import static net.minecraft.world.gen.feature.IFeatureConfig.NO_FEATURE_CONFIG;

import java.util.Random;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public abstract class ModThickTree extends ModTree {

	public boolean canPlaceTree(IWorld worldIn, Set<BlockPos> set) {
		Block sapling = this.getSapingBlock();

		if (set.size() > 1)
			return set.stream().map(worldIn::getBlockState).map(BlockState::getBlock).allMatch(sapling::equals);

		return false;
	}

	public abstract Block getSapingBlock();

	public Set<BlockPos> getSaplings(IWorld world, BlockPos pos) {
		Block block = this.getSapingBlock();

		for (Direction direction : HORIZONTAL) {
			BlockPos offset = pos.offset(direction);
			BlockState offState = world.getBlockState(offset);

			if (offState.getBlock() == block)
				for (Direction rotation : Lists.newArrayList(direction.rotateY(), direction.rotateYCCW())) {
					BlockPos side = offset.offset(rotation);
					BlockPos aside = pos.offset(rotation);

					BlockState sideState = world.getBlockState(side);
					BlockState asideState = world.getBlockState(aside);

					if (sideState.getBlock() == block && asideState.getBlock() == block)
						return Sets.newHashSet(pos, offset, side, aside);
				}
		}

		return Sets.newHashSet(pos);
	}

	protected abstract TemplateTreeFeature getThickTreeFeature(Random random);

	// TODO call grow method
	@Override
	public boolean spawn(IWorld worldIn, BlockPos pos, BlockState blockUnder, Random random) {
		ChunkGenerator<?> generator = worldIn.getChunkProvider().getChunkGenerator();
		Set<BlockPos> set = this.getSaplings(worldIn, pos);

		AbstractTreeFeature<NoFeatureConfig> tree = this.getTreeFeature(random);
		TemplateTreeFeature thickTree = this.getThickTreeFeature(random);

		if (thickTree != null && this.canPlaceTree(worldIn, set))
			return thickTree.place(worldIn, generator, random, pos, NO_FEATURE_CONFIG);
		else if (tree != null && worldIn.setBlockState(pos, AIR.getDefaultState(), 4))
			return tree.place(worldIn, generator, random, pos, NO_FEATURE_CONFIG);

		return false;
	}

}
