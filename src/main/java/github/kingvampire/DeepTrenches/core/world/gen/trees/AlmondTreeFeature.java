package github.kingvampire.DeepTrenches.core.world.gen.trees;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.core.init.ModBlocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class AlmondTreeFeature extends TemplateTreeFeature {

	private static final ResourceLocation MEDIUM_TOP = new ResourceLocation(MODID, "trees/almond/medium_top");

	public AlmondTreeFeature() {
		super(ModBlocks.ALMOND_LOG.getDefaultState(), 4, 15, MEDIUM_TOP);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.iterator().next();

		return pos.north(6).west(4).up(height);
	}

}
