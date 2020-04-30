package github.kingvampire.DeepTrenches.core.world.gen.fungus;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SUNRISE_FUNGUS_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Random;
import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.StraightFungusFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public class SunriseFungusFeature extends StraightFungusFeature {

	private static final ResourceLocation BASE = new ResourceLocation(MODID, "fungus/sunrise/base");
	private static final ResourceLocation LARGE_TOP = new ResourceLocation(MODID, "fungus/sunrise/large_top");
	private static final ResourceLocation SMALL_TOP = new ResourceLocation(MODID, "fungus/sunrise/small_top");

	public SunriseFungusFeature(Axis axis, ModThickTree thickTree) {
		super(axis, SUNRISE_FUNGUS_LOG.getDefaultState(), thickTree);

		this.setBaseId(BASE);
	}

	@Override
	public BlockPos getBaseOffset(ServerWorld server, Set<BlockPos> positions) {
		return positions.stream().sorted().iterator().next();
	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {
		return server.rand.nextInt(5);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = super.getOffset(server, positions, template, height);

		TemplateManager manager = server.getStructureTemplateManager();

		if (manager.getTemplate(SMALL_TOP) == template)
			return pos.north(2).west(3);

		return pos.north(3).west(4);
	}

	@Override
	public ResourceLocation getTemplateId(TemplateManager manager, Random random) {
		return random.nextInt(3) == 0 ? LARGE_TOP : SMALL_TOP;
	}

	@Override
	public BlockPos getTrunkOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = super.getTrunkOffset(server, positions, template, height);

		return pos.south();
	}

}
