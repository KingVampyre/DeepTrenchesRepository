package github.kingvampire.DeepTrenches.core.world.gen.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.TEAK_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.TEAK_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.block.LeavesBlock.DISTANCE;
import static net.minecraft.util.Direction.Plane.HORIZONTAL;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class TeakTreeFeature extends TemplateTreeFeature {

	private static final ResourceLocation SMALL_TOP = new ResourceLocation(MODID, "trees/teak/small_top");

	public TeakTreeFeature() {
		super(TEAK_LOG.getDefaultState(), 2, 5, SMALL_TOP);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.iterator().next();
		BlockPos size = template.getSize();

		int x = size.getX() / 2;
		int z = size.getZ() / 2;

		return pos.west(x).north(z).up(height);
	}

	@Override
	public boolean placeTrunk(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {

		if (super.placeTrunk(set, worldIn, position, height)) {
			for (int i = 1; i < height; i++) {
				BlockPos pos = position.up(i);

				for (Direction direction : HORIZONTAL) {
					BlockPos offset = pos.offset(direction);
					BlockState state = worldIn.getBlockState(offset);

					if (state.getBlock() == TEAK_LEAVES)
						continue;
					else if (worldIn.rand.nextInt(5) == 0)
						worldIn.setBlockState(offset, TEAK_LEAVES.getDefaultState().with(DISTANCE, 1));
				}
			}
		}

		return true;
	}

}
