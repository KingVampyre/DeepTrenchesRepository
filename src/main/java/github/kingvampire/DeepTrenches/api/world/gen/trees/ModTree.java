package github.kingvampire.DeepTrenches.api.world.gen.trees;

import static net.minecraft.block.Blocks.AIR;
import static net.minecraft.world.gen.feature.IFeatureConfig.NO_FEATURE_CONFIG;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public abstract class ModTree extends Tree {

	// TODO call grow method
	@Override
	public boolean spawn(IWorld worldIn, BlockPos pos, BlockState blockUnder, Random random) {
		AbstractTreeFeature<NoFeatureConfig> feature = this.getTreeFeature(random);
		ChunkGenerator<?> generator = worldIn.getChunkProvider().getChunkGenerator();

		if (feature == null)
			return false;
		else if (worldIn.setBlockState(pos, AIR.getDefaultState(), 4))
			return feature.place(worldIn, generator, random, pos, NO_FEATURE_CONFIG);

		return false;
	}

	// TODO grow method

}
