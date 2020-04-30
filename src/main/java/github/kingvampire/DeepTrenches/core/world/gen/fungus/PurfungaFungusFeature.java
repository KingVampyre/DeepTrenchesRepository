package github.kingvampire.DeepTrenches.core.world.gen.fungus;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PURFUNGA_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.util.Direction.NORTH;
import static net.minecraft.util.Direction.Plane.HORIZONTAL;

import java.util.Random;
import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.StraightFungusFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public class PurfungaFungusFeature extends StraightFungusFeature {

	private static final ResourceLocation BASE = new ResourceLocation(MODID, "fungus/purfunga/base");
	private static final ResourceLocation LARGE_BRANCH = new ResourceLocation(MODID, "fungus/purfunga/large_branch");
	private static final ResourceLocation SMALL_BRANCH = new ResourceLocation(MODID, "fungus/purfunga/small_branch");
	private static final ResourceLocation TOP = new ResourceLocation(MODID, "fungus/purfunga/top");

	public PurfungaFungusFeature(Axis axis, ModThickTree thickTree) {
		super(axis, PURFUNGA_LOG.getDefaultState(), thickTree);

		this.setBaseId(BASE);
		this.setTemplateId(TOP);
	}

	@Override
	public BlockPos getBaseOffset(ServerWorld server, Set<BlockPos> positions) {
		BlockPos pos = positions.stream().sorted().iterator().next();

		return pos.west().north();
	}

	@Override
	public BlockPos getBranchOffset(ServerWorld server, Set<BlockPos> positions, Template template, BlockPos position,
			Direction direction, int height) {

		Direction yccw = direction.rotateYCCW();
		BlockPos offset = direction == NORTH ? position.east() : position.offset(yccw);

		return offset.up(height).south().east().offset(direction, 3);
	}

	@Override
	public ResourceLocation getBranchTemplateId(Random rand) {
		return rand.nextInt(5) == 0 ? LARGE_BRANCH : SMALL_BRANCH;

	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {
		return 3 + server.rand.nextInt(2);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = super.getOffset(server, positions, template, height);

		return pos.north(3).west(3);
	}

	@Override
	public BlockPos getTrunkOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = super.getTrunkOffset(server, positions, template, height);

		return pos.south().east();
	}

	@Override
	public boolean placeBranches(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {
		TemplateManager manager = worldIn.getStructureTemplateManager();

		BlockPos pos = set.stream().sorted().iterator().next();
		int minHeight = this.getBranchHeight();

		for (Direction direction : HORIZONTAL) {
			Axis axis = this.getAxis();

			if (direction.getAxis() == axis) {
				PlacementSettings settings = this.getBranchSettings(direction);
				ResourceLocation id = this.getBranchTemplateId(worldIn.rand);

				Template template = manager.getTemplate(id);
				BlockPos place = this.getBranchOffset(worldIn, set, template, pos, direction, minHeight);

				if (template != null && worldIn.isAirBlock(place))
					template.addBlocksToWorld(worldIn, place, settings);
			}
		}

		return true;
	}

}
