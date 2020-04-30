package github.kingvampire.DeepTrenches.api.world.gen.features;

import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Plane.HORIZONTAL;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public abstract class ModFungusFeature extends TemplateBranchThickTreeFeature {

	private Axis axis;
	private ResourceLocation baseId;

	public ModFungusFeature(Axis axis, BlockState steam, ModThickTree thickTree) {
		super(steam, thickTree);

		this.axis = axis;
	}

	public Axis getAxis() {
		return this.axis;
	}

	public ResourceLocation getBaseId() {
		return this.baseId;
	}

	public abstract BlockPos getBaseOffset(ServerWorld server, Set<BlockPos> positions);

	@Override
	public int getBranchChance() {
		return 0;
	}

	@Override
	public int getBranchHeight() {
		return 2;
	}

	@Override
	public BlockPos getBranchOffset(ServerWorld server, Set<BlockPos> positions, Template template, BlockPos position,
			Direction direction, int height) {

		return null;
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		TemplateManager manager = server.getStructureTemplateManager();
		BlockPos pos = positions.stream().sorted().iterator().next();

		Template base = manager.getTemplate(this.getBaseId());
		BlockPos size = base.getSize();

		return pos.up(height + size.getY());
	}

	@Override
	public boolean placeBranches(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {
		int minHeight = this.getBranchHeight();

		for (BlockPos pos : set) {
			for (Direction direction : HORIZONTAL) {
				Axis axis = this.getAxis();

				if (direction.getAxis() == axis) {
					Direction rotation = axis == X ? direction.rotateY() : direction.rotateYCCW();

					BlockPos border = pos.up(minHeight).offset(direction, 3);
					BlockPos log = pos.up(minHeight).offset(rotation);

					if (worldIn.isAirBlock(border) && !worldIn.isAirBlock(log)) {
						TemplateManager manager = worldIn.getStructureTemplateManager();

						PlacementSettings settings = this.getBranchSettings(direction);
						ResourceLocation id = this.getBranchTemplateId(worldIn.rand);

						if (id != null) {
							Template template = manager.getTemplate(id);

							BlockPos place = this.getBranchOffset(worldIn, set, template, border, direction, minHeight);

							if (template != null && place != null && worldIn.isAirBlock(place))
								template.addBlocksToWorld(worldIn, place, settings);
						}
					}
				}
			}
		}

		return true;
	}

	@Override
	public boolean placeTrunk(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {
		TemplateManager manager = worldIn.getStructureTemplateManager();

		if (this.baseId != null) {
			BlockPos offset = this.getBaseOffset(worldIn, set);
			Template template = manager.getTemplate(this.baseId);

			if (template.addBlocksToWorld(worldIn, offset, this.getSettings(), 2))
				return this.placeBranches(set, worldIn, position, height);
		}

		return false;
	}

	public void setBaseId(ResourceLocation baseId) {
		this.baseId = baseId;
	}

}
