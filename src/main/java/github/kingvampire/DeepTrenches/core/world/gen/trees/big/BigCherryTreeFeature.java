package github.kingvampire.DeepTrenches.core.world.gen.trees.big;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CHERRY_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateThickTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class BigCherryTreeFeature extends TemplateThickTreeFeature {

	private static final ResourceLocation LARGE_TOP = new ResourceLocation(MODID, "trees/cherry/large_top");

	public BigCherryTreeFeature(ModThickTree thickTree) {
		super(CHERRY_LOG.getDefaultState(), thickTree, 7, 21, LARGE_TOP);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.stream().sorted().iterator().next();

		return pos.north(5).west(4).up(height);
	}

}
