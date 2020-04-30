package github.kingvampire.DeepTrenches.core.world.gen.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BLACK_BIRCH_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class BlackBirchTreeFeature extends TemplateTreeFeature {

	private static final ResourceLocation SMALL_TOP = new ResourceLocation(MODID, "trees/black_birch/small_top");

	public BlackBirchTreeFeature() {
		super(BLACK_BIRCH_LOG.getDefaultState(), 8, 13, SMALL_TOP);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.stream().sorted().findFirst().get();
		BlockPos size = template.getSize();

		int x = size.getX() / 2;
		int z = size.getZ() / 2;

		return pos.north(x).west(z).up(height);
	}

}
