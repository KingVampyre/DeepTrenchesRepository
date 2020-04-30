package github.kingvampire.DeepTrenches.core.world.gen.trees.big;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.util.Direction.NORTH;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Plane.HORIZONTAL;
import static net.minecraft.util.Mirror.FRONT_BACK;
import static net.minecraft.util.Rotation.CLOCKWISE_90;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateBranchThickTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public class BigFuchsitraTreeFeature extends TemplateBranchThickTreeFeature {

	private static final ResourceLocation BASE = new ResourceLocation(MODID, "trees/fuchsitra/base");
	private static final ResourceLocation BRANCH = new ResourceLocation(MODID, "trees/fuchsitra/branch");
	private static final ResourceLocation LARGE_TOP = new ResourceLocation(MODID, "trees/fuchsitra/large_top");

	private Axis axis;

	public BigFuchsitraTreeFeature(ModThickTree thickTree, Axis axis) {
		super(FUCHSITRA_LOG.getDefaultState(), thickTree, 10, 15, null);

		this.axis = axis;

		this.setTemplateId(LARGE_TOP);
		this.setBranchTemplateId(BRANCH);
	}

	@Override
	public int getBranchChance() {
		return 0;
	}

	@Override
	public int getBranchHeight() {
		return 2;
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.stream().sorted().iterator().next().up(height);

		return this.axis == X ? pos.west(7).north(3) : pos.east(4).south(8);
	}

	@Override
	public PlacementSettings getSettings() {
		PlacementSettings settings = super.getSettings();

		return this.axis == X ? settings : settings.setRotation(CLOCKWISE_90).setMirror(FRONT_BACK);
	}

	@Override
	public boolean placeBranches(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {
		int minHeight = this.getBranchHeight();

		for (BlockPos pos : set) {
			for (Direction direction : HORIZONTAL) {

				if (direction.getAxis() == this.axis) {
					Direction rotation = this.axis == X ? direction.rotateY() : direction.rotateYCCW();

					BlockPos border = pos.up(minHeight).offset(direction, 3);
					BlockPos log = pos.up(minHeight).offset(rotation);

					if (worldIn.isAirBlock(border) && !worldIn.isAirBlock(log)) {
						TemplateManager templateManager = worldIn.getStructureTemplateManager();

						PlacementSettings settings = this.getBranchSettings(direction);
						ResourceLocation id = this.getBranchTemplateId(worldIn.rand);

						Template template = templateManager.getTemplate(id);
						BlockPos place = this.getBranchOffset(worldIn, set, template, border, direction, minHeight);

						if (template != null && worldIn.isAirBlock(place))
							template.addBlocksToWorld(worldIn, place, settings);
					}
				}
			}
		}

		return true;
	}

	@Override
	public boolean placeTrunk(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {
		TemplateManager manager = worldIn.getStructureTemplateManager();
		BlockPos pos = set.stream().sorted().iterator().next();

		Template template = manager.getTemplate(BASE);
		BlockPos size = template.getSize();

		BlockPos offset = this.axis == X ? pos.north().west() : pos.south(2).east(2);

		if (super.placeTrunk(set, worldIn, position.up(size.getY()), height))
			return template.addBlocksToWorld(worldIn, offset, this.getSettings(), 2);

		return false;
	}

	@Override
	public BlockPos getBranchOffset(ServerWorld server, Set<BlockPos> positions, Template template, BlockPos position,
			Direction direction, int height) {

		Direction yccw = direction.rotateYCCW();

		return direction == NORTH ? position.east() : position.offset(yccw);
	}

}
