package github.kingvampire.DeepTrenches.core.world.gen.trees.thick;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SPROOM_LOG;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SPROOM_SPIKE;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.state.properties.BlockStateProperties.FACING;
import static net.minecraft.util.Direction.Plane.HORIZONTAL;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateBranchThickTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class SproomTreeFeature extends TemplateBranchThickTreeFeature {

	private static final ResourceLocation TOP = new ResourceLocation(MODID, "trees/sproom/top");

	public SproomTreeFeature(ModThickTree thickTree) {
		super(SPROOM_LOG.getDefaultState(), thickTree, 61, 71, TOP);
	}

	@Override
	public int getBranchChance() {
		return 3;
	}

	@Override
	public int getBranchHeight() {
		return 1;
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.stream().sorted().iterator().next();

		return pos.north(10).west(10).up(height);
	}

	public boolean placeBranches(Set<BlockPos> positions, ServerWorld worldIn, BlockPos position, int height) {
		int minHeight = this.getBranchHeight();

		for (int currHeight = minHeight; currHeight < height; currHeight++) {
			for (BlockPos pos : positions) {
				for (Direction direction : HORIZONTAL) {

					BlockPos border = pos.up(currHeight).offset(direction);
					int chance = this.getBranchChance();

					if (worldIn.isAirBlock(border) && worldIn.rand.nextInt(chance) == 0) {
						BlockPos offset = this.getBranchOffset(worldIn, positions, null, border, direction, height);

						if (offset != null && worldIn.isAirBlock(offset)) {
							int spikes = 1 + worldIn.rand.nextInt(4);

							for (int i = 0; i < spikes; i++) {
								BlockState state = SPROOM_SPIKE.getDefaultState().with(FACING, direction);

								worldIn.setBlockState(offset.offset(direction, i), state);
							}
						}
					}
				}
			}
		}

		return true;
	}

	@Override
	public BlockPos getBranchOffset(ServerWorld server, Set<BlockPos> positions, Template template, BlockPos position,
			Direction direction, int height) {

		return position;
	}

}
