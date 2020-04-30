package github.kingvampire.DeepTrenches.core.world.gen.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PIN_CHERRY_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class PinCherryTreeFeature extends TemplateTreeFeature {

	private static final ResourceLocation MEDIUM_TOP = new ResourceLocation(MODID, "trees/pin_cherry/medium_top");

	public PinCherryTreeFeature() {
		super(PIN_CHERRY_LOG.getDefaultState(), 4, 15, MEDIUM_TOP);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.iterator().next();

		return pos.north(5).west(5).up(height);
	}

}
