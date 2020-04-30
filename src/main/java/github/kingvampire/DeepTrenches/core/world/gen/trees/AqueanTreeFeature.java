package github.kingvampire.DeepTrenches.core.world.gen.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.enums.TreeSize;
import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class AqueanTreeFeature extends TemplateTreeFeature {

	private TreeSize size;

	public AqueanTreeFeature(TreeSize size) {
		super(AQUEAN_LOG.getDefaultState(), new ResourceLocation(MODID, "trees/aquean/" + size + "_top"));

		this.size = size;
	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {

		switch (this.size) {
		case LARGE:
			return 7 + server.rand.nextInt(2);
		case MEDIUM:
			return 4 + server.rand.nextInt(2);
		case SMALL:
			return 2 + server.rand.nextInt(2);
		default:
			return super.getHeight(server, positions);
		}
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = super.getOffset(server, positions, template, height);

		BlockPos size = template.getSize();

		int x = size.getX() / 2;
		int z = size.getZ() / 2;

		return pos.up(height).north(z).west(x);
	}

}
