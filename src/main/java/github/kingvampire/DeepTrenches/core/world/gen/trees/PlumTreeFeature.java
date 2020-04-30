package github.kingvampire.DeepTrenches.core.world.gen.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PLUM_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.enums.TreeSize;
import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class PlumTreeFeature extends TemplateTreeFeature {

	private TreeSize size;

	public PlumTreeFeature(TreeSize size) {
		super(PLUM_LOG.getDefaultState(), 0, 0, new ResourceLocation(MODID, "trees/plum/" + size + "_top"));

		this.size = size;
	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {

		switch (this.size) {
		case LARGE:
			return 5 + server.rand.nextInt(2);
		case MEDIUM:
			return 3 + server.rand.nextInt(2);
		case SMALL:
			return 1 + server.rand.nextInt(2);
		default:
			return super.getHeight(server, positions);
		}
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.iterator().next();
		BlockPos size = template.getSize();

		int x = size.getX() / 2;
		int z = size.getZ() / 2;

		return pos.west(x).north(z).up(height);
	}

}
