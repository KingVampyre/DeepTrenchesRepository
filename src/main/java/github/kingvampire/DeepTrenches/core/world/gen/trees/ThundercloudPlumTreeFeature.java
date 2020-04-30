package github.kingvampire.DeepTrenches.core.world.gen.trees;

import static github.kingvampire.DeepTrenches.api.enums.TreeSize.SMALL;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.THUNDERCLOUD_PLUM_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.util.Direction.NORTH;
import static net.minecraft.util.Direction.WEST;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;
import static net.minecraft.util.Direction.Plane.HORIZONTAL;
import static net.minecraft.util.Mirror.FRONT_BACK;
import static net.minecraft.util.Rotation.CLOCKWISE_180;
import static net.minecraft.util.Rotation.CLOCKWISE_90;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.enums.TreeSize;
import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateBranchTreeFeature;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class ThundercloudPlumTreeFeature extends TemplateBranchTreeFeature {

	private Axis axis;
	private TreeSize size;

	public ThundercloudPlumTreeFeature(Axis axis, TreeSize size) {
		super(THUNDERCLOUD_PLUM_LOG.getDefaultState(), null);

		this.axis = axis;
		this.size = size;

		this.setTemplateId(new ResourceLocation(MODID, "trees/thundercloud_plum/" + size + "_top"));
	}

	@Override
	public Template getBranchTemplate(ServerWorld worldIn) {
		TreeSize size = TreeSize.values()[worldIn.rand.nextInt(TreeSize.values().length)];
		ResourceLocation id = new ResourceLocation(MODID, "trees/thundercloud_plum/" + size + "_branch");

		return worldIn.getStructureTemplateManager().getTemplate(id);
	}

	@Override
	public PlacementSettings getBranchSettings(Direction direction) {
		PlacementSettings settings = super.getSettings();

		if (direction.getAxis() == Z) {
			settings.setRotation(CLOCKWISE_90);

			return direction == NORTH ? settings : settings.setMirror(FRONT_BACK);
		} else if (direction.getAxis() == X)
			return direction == WEST ? settings : settings.setRotation(CLOCKWISE_180);

		return settings;
	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {

		switch (this.size) {
		case LARGE:
			return 10 + server.rand.nextInt(4);
		case MEDIUM:
			return 8 + server.rand.nextInt(5);
		case SMALL:
			return 6 + server.rand.nextInt(3);
		default:
			return super.getHeight(server, positions);
		}
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.iterator().next().up(height);
		BlockPos size = template.getSize();

		int x = size.getX() / 2;
		int z = size.getZ() / 2;

		int offset = this.size != SMALL ? 2 : 1;

		return this.axis == X ? pos.west(x).north(z) : pos.east(x).south(z).west(offset).south(offset);
	}

	@Override
	public PlacementSettings getSettings() {
		PlacementSettings settings = super.getSettings();

		return this.axis == X ? settings : settings.setRotation(CLOCKWISE_90).setMirror(FRONT_BACK);
	}

	@Override
	public boolean placeBranches(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {

		if (this.size != SMALL)
			for (Direction direction : HORIZONTAL) {
				Template template = this.getBranchTemplate(worldIn);
				BlockPos size = template.getSize();

				int y = size.getY();

				if (direction.getAxis() == this.axis) {
					PlacementSettings settings = this.getBranchSettings(direction);
					BlockPos pos = position.up(height - y).offset(direction, 2);

					if (worldIn.isAirBlock(pos))
						template.addBlocksToWorld(worldIn, pos, settings);
				}
			}

		return true;
	}

}
