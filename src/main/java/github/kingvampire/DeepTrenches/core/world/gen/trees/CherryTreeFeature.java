package github.kingvampire.DeepTrenches.core.world.gen.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CHERRY_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class CherryTreeFeature extends TemplateTreeFeature {

	private static final ResourceLocation MEDIUM_TOP = new ResourceLocation(MODID, "trees/cherry/medium_top");

	public CherryTreeFeature() {
		super(CHERRY_LOG.getDefaultState(), 7, 15, MEDIUM_TOP);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.iterator().next();

		return pos.north(4).west(5).up(height);
	}

}
