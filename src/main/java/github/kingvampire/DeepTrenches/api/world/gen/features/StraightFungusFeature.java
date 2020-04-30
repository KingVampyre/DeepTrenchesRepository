package github.kingvampire.DeepTrenches.api.world.gen.features;

import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public abstract class StraightFungusFeature extends ModFungusFeature {

	public StraightFungusFeature(Axis axis, BlockState steam, ModThickTree thickTree) {
		super(axis, steam, thickTree);
	}

	public BlockPos getTrunkOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		TemplateManager manager = server.getStructureTemplateManager();

		BlockPos pos = positions.stream().sorted().iterator().next();
		Template base = manager.getTemplate(this.getBaseId());

		int y = base.getSize().getY();

		return pos.up(y);
	}

	@Override
	public boolean placeTrunk(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {
		TemplateManager manager = worldIn.getStructureTemplateManager();
		Template base = manager.getTemplate(this.getBaseId());

		BlockPos offset = this.getTrunkOffset(worldIn, set, base, height);
		Stream<BlockPos> stream = IntStream.range(0, height).mapToObj(offset::up);

		if (super.placeTrunk(set, worldIn, position, height))
			return stream.allMatch(up -> worldIn.setBlockState(up, this.getLog()));

		return false;
	}

}
