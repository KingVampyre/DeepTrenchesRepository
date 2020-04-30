package github.kingvampire.DeepTrenches.core.world.gen.trees.thick;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PELTOGYNE_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.util.Direction.EAST;
import static net.minecraft.util.Direction.NORTH;
import static net.minecraft.util.Direction.SOUTH;
import static net.minecraft.util.Direction.WEST;
import static net.minecraft.util.Direction.Plane.HORIZONTAL;

import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateBranchThickTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public class PeltogyneTreeFeature extends TemplateBranchThickTreeFeature {

	private static final ResourceLocation LARGE_BRANCH = new ResourceLocation(MODID, "trees/peltogyne/large_branch");
	private static final ResourceLocation MEDIUM_BRANCH = new ResourceLocation(MODID, "trees/peltogyne/medium_branch");
	private static final ResourceLocation SMALL_BRANCH = new ResourceLocation(MODID, "trees/peltogyne/small_branch");
	private static final ResourceLocation TOP = new ResourceLocation(MODID, "trees/peltogyne/top");

	public PeltogyneTreeFeature(ModThickTree thickTree) {
		super(PELTOGYNE_LOG.getDefaultState(), thickTree, 20, 40, TOP);
	}

	@Override
	public int getBranchChance() {
		return 0;
	}

	@Override
	public int getBranchHeight() {
		return 0;
	}

	@Override
	public BlockPos getBranchOffset(ServerWorld server, Set<BlockPos> positions, Template template, BlockPos position,
			Direction direction, int height) {

		TemplateManager manager = server.getStructureTemplateManager();
		BlockPos pos = positions.stream().sorted().iterator().next();

		BlockPos offset = pos.up(height);

		if (direction == NORTH)
			return offset.north().east(manager.getTemplate(MEDIUM_BRANCH) == template ? 3 : 4);
		else if (direction == SOUTH)
			return offset.south(2).east(manager.getTemplate(SMALL_BRANCH) == template ? 3 : 4);
		else if (direction == WEST)
			return offset.south(manager.getTemplate(SMALL_BRANCH) == template ? 3 : 4).west();
		else if (direction == EAST)
			return offset.north(manager.getTemplate(SMALL_BRANCH) == template ? 2 : 3).east(2);

		return offset;
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.stream().sorted().iterator().next();

		return pos.north(5).west(5).up(height);
	}

	@Override
	public boolean placeBranches(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {
		TemplateManager templateManager = worldIn.getStructureTemplateManager();

		for (Direction direction : HORIZONTAL) {
			OptionalInt optional = worldIn.rand.ints(height - 12, height - 5).findAny();

			if (optional.isPresent()) {
				ResourceLocation id = this.getBranchTemplateId(worldIn.rand);

				Template template = templateManager.getTemplate(id);
				int y = optional.getAsInt();

				BlockPos offset = this.getBranchOffset(worldIn, set, template, position, direction, y);
				PlacementSettings settings = this.getBranchSettings(direction);

				template.addBlocksToWorld(worldIn, offset, settings);
			}
		}

		return true;
	}

	@Override
	public ResourceLocation getBranchTemplateId(Random rand) {

		if (rand.nextInt(5) == 0)
			return LARGE_BRANCH;
		else if (rand.nextInt(3) == 0)
			return MEDIUM_BRANCH;

		return SMALL_BRANCH;
	}

}
