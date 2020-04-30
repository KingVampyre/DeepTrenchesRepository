package github.kingvampire.DeepTrenches.core.world.gen.fungus;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ANAMEATA_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.enums.TreeSize;
import github.kingvampire.DeepTrenches.api.world.gen.features.StraightFungusFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class AnameataFungusFeature extends StraightFungusFeature {

	private TreeSize size;

	public AnameataFungusFeature(Axis axis, TreeSize size, ModThickTree steam) {
		super(axis, ANAMEATA_LOG.getDefaultState(), steam);

		this.size = size;

		this.setBaseId(new ResourceLocation(MODID, "fungus/anameata/" + size + "_base"));
		this.setTemplateId(new ResourceLocation(MODID, "fungus/anameata/" + size + "_top"));
	}

	@Override
	public BlockPos getBaseOffset(ServerWorld server, Set<BlockPos> positions) {
		BlockPos pos = positions.stream().sorted().iterator().next();

		return pos.west().north();
	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {

		switch (this.size) {
		case LARGE:
			return 8 + server.rand.nextInt(12);
		case MEDIUM:
			return 4 + server.rand.nextInt(8);
		case SMALL:
			return 2 + server.rand.nextInt(4);
		default:
			return super.getHeight(server, positions);
		}
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = super.getOffset(server, positions, template, height);

		switch (this.size) {
		case LARGE:
			return pos.north(8).west(8);
		case MEDIUM:
			return pos.north(7).west(7);
		case SMALL:
			return pos.north(6).west(7);
		default:
			return pos;
		}
	}

	public BlockPos getTrunkOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = super.getTrunkOffset(server, positions, template, height);

		switch (this.size) {
		case LARGE:
		case MEDIUM:
			return pos.south().east();
		default:
			return pos;
		}
	}

}
