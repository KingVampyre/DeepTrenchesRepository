package github.kingvampire.DeepTrenches.core.world.gen.trees.thick;

import static github.kingvampire.DeepTrenches.api.enums.TreeSize.LARGE;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.TEAK_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.util.Direction.NORTH;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;
import static net.minecraft.util.Mirror.FRONT_BACK;
import static net.minecraft.util.Rotation.CLOCKWISE_90;

import java.util.Random;
import java.util.Set;

import github.kingvampire.DeepTrenches.api.enums.TreeSize;
import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateBranchThickTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class TeakThickTreeFeature extends TemplateBranchThickTreeFeature {

	private static final ResourceLocation LARGE_BRANCH = new ResourceLocation(MODID, "trees/teak/large_branch");
	private static final ResourceLocation LARGE_TOP = new ResourceLocation(MODID, "trees/teak/large_top");
	private static final ResourceLocation MEDIUM_BRANCH = new ResourceLocation(MODID, "trees/teak/medium_branch");
	private static final ResourceLocation MEDIUM_TOP = new ResourceLocation(MODID, "trees/teak/medium_top");
	private static final ResourceLocation SMALL_BRANCH = new ResourceLocation(MODID, "trees/teak/small_branch");

	private Axis axis;
	private TreeSize size;

	public TeakThickTreeFeature(ModThickTree thickTree, Axis axis, TreeSize size) {
		super(TEAK_LOG.getDefaultState(), thickTree, 0, 0, null);

		this.axis = axis;
		this.size = size;

		this.setTemplateId(size == LARGE ? LARGE_TOP : MEDIUM_TOP);
	}

	@Override
	public int getBranchChance() {
		return 10;
	}

	@Override
	public int getBranchHeight() {
		return 3;
	}

	@Override
	public ResourceLocation getBranchTemplateId(Random rand) {

		if (rand.nextInt(5) == 0)
			return LARGE_BRANCH;
		else if (rand.nextInt(3) == 0)
			return MEDIUM_BRANCH;

		return SMALL_BRANCH;
	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {

		switch (this.size) {
		case LARGE:
			return 18 + server.rand.nextInt(4);
		case MEDIUM:
			return 10 + server.rand.nextInt(5);
		default:
			return super.getHeight(server, positions);
		}
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.stream().sorted().iterator().next().up(height);
		BlockPos size = template.getSize();

		int x = size.getX() / 2;
		int z = size.getZ() / 2;

		switch (this.size) {
		case LARGE:
			return this.axis == X ? pos.north(x - 1).west(z - 1) : pos.south(x).east(z);
		case MEDIUM:
			return this.axis == X ? pos.north(x / 2).west(z + 3) : pos.south(x / 2 + 5).east(z);
		default:
			return super.getOffset(server, positions, template, height);
		}
	}

	@Override
	public PlacementSettings getSettings() {
		PlacementSettings settings = super.getSettings();

		if (this.axis == Z)
			settings.setRotation(CLOCKWISE_90).setMirror(FRONT_BACK);

		return settings;
	}

	@Override
	public BlockPos getBranchOffset(ServerWorld server, Set<BlockPos> positions, Template template, BlockPos position,
			Direction direction, int height) {

		Direction yccw = direction.rotateYCCW();

		return direction == NORTH ? position.east() : position.offset(yccw);
	}

}
