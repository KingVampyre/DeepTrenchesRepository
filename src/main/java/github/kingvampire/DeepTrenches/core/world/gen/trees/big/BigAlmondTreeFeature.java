package github.kingvampire.DeepTrenches.core.world.gen.trees.big;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ALMOND_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateThickTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class BigAlmondTreeFeature extends TemplateThickTreeFeature {

	private static final ResourceLocation LARGE_TOP = new ResourceLocation(MODID, "trees/almond/large_top");

	public BigAlmondTreeFeature(ModThickTree thickTree) {
		super(ALMOND_LOG.getDefaultState(), thickTree, 4, 17, LARGE_TOP);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.stream().sorted().iterator().next();

		return pos.north(3).west(6).up(height);
	}

}
