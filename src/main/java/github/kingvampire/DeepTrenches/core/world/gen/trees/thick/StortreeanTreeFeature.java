package github.kingvampire.DeepTrenches.core.world.gen.trees.thick;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STORTREEAN_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.util.Direction.NORTH;
import static net.minecraft.util.Direction.WEST;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;
import static net.minecraft.util.Mirror.FRONT_BACK;
import static net.minecraft.util.Rotation.CLOCKWISE_180;
import static net.minecraft.util.Rotation.CLOCKWISE_90;
import static net.minecraft.util.Rotation.COUNTERCLOCKWISE_90;

import java.util.Random;
import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateBranchThickTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class StortreeanTreeFeature extends TemplateBranchThickTreeFeature {

	private static final ResourceLocation LARGE_BRANCH = new ResourceLocation(MODID, "trees/stortreean/large_branch");
	private static final ResourceLocation MEDIUM_BRANCH = new ResourceLocation(MODID, "trees/stortreean/medium_branch");
	private static final ResourceLocation SMALL_BRANCH = new ResourceLocation(MODID, "trees/stortreean/small_branch");
	private static final ResourceLocation TOP = new ResourceLocation(MODID, "trees/stortreean/top");

	private Axis axis;

	public StortreeanTreeFeature(ModThickTree thickTree, Axis axis) {
		super(STORTREEAN_LOG.getDefaultState(), thickTree, 20, 44, TOP);

		this.axis = axis;
	}

	@Override
	public int getBranchChance() {
		return 35;
	}

	@Override
	public BlockPos getBranchOffset(ServerWorld server, Set<BlockPos> positions, Template template, BlockPos position,
			Direction direction, int height) {

		Direction rotation = direction == WEST ? direction.rotateY() : direction.rotateYCCW();
		BlockPos size = template.getSize();
		int z = size.getZ();

		return position.offset(direction, z - 1).offset(rotation);
	}

	@Override
	public ResourceLocation getBranchTemplateId(Random rand) {

		if (rand.nextInt(9) == 0)
			return LARGE_BRANCH;
		if (rand.nextInt(5) == 0)
			return MEDIUM_BRANCH;

		return SMALL_BRANCH;
	}

	@Override
	public PlacementSettings getBranchSettings(Direction direction) {
		PlacementSettings settings = super.getSettings();

		if (direction.getAxis() == X) {
			return direction == WEST ? settings.setRotation(COUNTERCLOCKWISE_90).setMirror(FRONT_BACK)
					: settings.setRotation(CLOCKWISE_90);
		} else if (direction.getAxis() == Z)
			return direction == NORTH ? settings : settings.setRotation(CLOCKWISE_180);

		return settings;
	}

	@Override
	public int getBranchHeight() {
		return 5;
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.stream().sorted().iterator().next().up(height);
		BlockPos size = template.getSize();

		int x = size.getX() / 2;
		int z = size.getZ() / 2;

		return this.axis == X ? pos.north(x).west(z).south(2) : pos.south(x).east(z);
	}

	@Override
	public PlacementSettings getSettings() {
		PlacementSettings settings = super.getSettings();

		if (this.axis == Z)
			settings.setRotation(CLOCKWISE_90).setMirror(FRONT_BACK);

		return settings;
	}

}
