package github.kingvampire.DeepTrenches.api.world.gen.features;

import static net.minecraft.util.Direction.NORTH;
import static net.minecraft.util.Direction.WEST;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;
import static net.minecraft.util.Mirror.FRONT_BACK;
import static net.minecraft.util.Rotation.CLOCKWISE_180;
import static net.minecraft.util.Rotation.CLOCKWISE_90;

import java.util.Random;
import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Plane;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public abstract class TemplateBranchThickTreeFeature extends TemplateThickTreeFeature {

	private ResourceLocation branchId;

	public TemplateBranchThickTreeFeature(BlockState log, ModThickTree thickTree) {
		super(log, thickTree);
	}

	public TemplateBranchThickTreeFeature(BlockState log, ModThickTree thickTree, int minHeight, int maxHeight,
			ResourceLocation templateId) {

		super(log, thickTree, minHeight, maxHeight, templateId);
	}

	public abstract int getBranchChance();

	public abstract int getBranchHeight();

	public abstract BlockPos getBranchOffset(ServerWorld server, Set<BlockPos> positions, Template template,
			BlockPos position, Direction direction, int height);

	public PlacementSettings getBranchSettings(Direction direction) {
		PlacementSettings settings = super.getSettings();

		if (direction.getAxis() == Z) {
			settings.setRotation(CLOCKWISE_90);

			return direction == NORTH ? settings.setMirror(FRONT_BACK) : settings;
		} else if (direction.getAxis() == X)
			return direction == WEST ? settings.setRotation(CLOCKWISE_180) : settings;

		return settings;
	}

	public ResourceLocation getBranchTemplateId(Random rand) {
		return this.branchId;
	}

	public boolean placeBranches(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {
		int minHeight = this.getBranchHeight();

		for (int i = minHeight; i < height; i++) {
			for (BlockPos pos : set) {
				for (Direction direction : Plane.HORIZONTAL) {

					BlockPos border = pos.up(i).offset(direction);
					int chance = this.getBranchChance();

					if (worldIn.isAirBlock(border) && worldIn.rand.nextInt(chance) == 0) {
						TemplateManager manager = worldIn.getStructureTemplateManager();

						PlacementSettings settings = this.getBranchSettings(direction);
						ResourceLocation id = this.getBranchTemplateId(worldIn.rand);
						Template template = manager.getTemplate(id);

						BlockPos place = this.getBranchOffset(worldIn, set, template, border, direction, height);

						if (template != null && place != null && worldIn.isAirBlock(place))
							template.addBlocksToWorld(worldIn, place, settings);
					}
				}
			}
		}

		return true;
	}

	@Override
	public boolean placeTrunk(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {

		if (super.placeTrunk(set, worldIn, position, height))
			return this.placeBranches(set, worldIn, position, height);

		return false;
	}

	public void setBranchTemplateId(ResourceLocation branchId) {
		this.branchId = branchId;
	}

}
