package github.kingvampire.DeepTrenches.core.world.gen.trees;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_LEAVES;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.block.LeavesBlock.DISTANCE;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Plane.HORIZONTAL;
import static net.minecraft.util.Mirror.FRONT_BACK;
import static net.minecraft.util.Rotation.CLOCKWISE_90;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class FuchsitraTreeFeature extends TemplateTreeFeature {

	private static final ResourceLocation SMALL_TOP = new ResourceLocation(MODID, "trees/fuchsitra/small_top");

	private Axis axis;

	public FuchsitraTreeFeature(Axis axis) {
		super(FUCHSITRA_LOG.getDefaultState(), 10, 15, SMALL_TOP);

		this.axis = axis;
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.stream().sorted().iterator().next().up(height);

		return this.axis == X ? pos.north().west(4) : pos.east().south(4);
	}

	@Override
	public PlacementSettings getSettings() {
		PlacementSettings settings = super.getSettings();

		return this.axis == X ? settings : settings.setRotation(CLOCKWISE_90).setMirror(FRONT_BACK);
	}

	@Override
	public boolean placeTrunk(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {

		if (super.placeTrunk(set, worldIn, position, height)) {
			for (int i = 1; i < height; i++) {
				BlockPos pos = position.up(i);

				for (Direction direction : HORIZONTAL) {
					BlockPos offset = pos.offset(direction);
					BlockState state = worldIn.getBlockState(offset);

					if (state.getBlock() == FUCHSITRA_LEAVES)
						continue;
					else if (worldIn.rand.nextInt(5) == 0)
						worldIn.setBlockState(offset, FUCHSITRA_LEAVES.getDefaultState().with(DISTANCE, 1));
				}
			}
		}

		return true;
	}

}
