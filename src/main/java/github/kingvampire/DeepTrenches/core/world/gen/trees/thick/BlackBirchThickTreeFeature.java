package github.kingvampire.DeepTrenches.core.world.gen.trees.thick;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BLACK_BIRCH_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.util.Direction.NORTH;

import java.util.Random;
import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateBranchThickTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class BlackBirchThickTreeFeature extends TemplateBranchThickTreeFeature {

	private static final ResourceLocation LARGE_BRANCH = new ResourceLocation(MODID, "trees/black_birch/large_branch");
	private static final ResourceLocation LARGE_TOP = new ResourceLocation(MODID, "trees/black_birch/large_top");
	private static final ResourceLocation SMALL_BRANCH = new ResourceLocation(MODID, "trees/black_birch/small_branch");

	public BlackBirchThickTreeFeature(ModThickTree thickTree) {
		super(BLACK_BIRCH_LOG.getDefaultState(), thickTree, 8, 21, LARGE_TOP);
	}

	@Override
	public int getBranchChance() {
		return 10;
	}

	@Override
	public int getBranchHeight() {
		return 1;
	}

	@Override
	public BlockPos getBranchOffset(ServerWorld server, Set<BlockPos> positions, Template template, BlockPos position,
			Direction direction, int height) {

		Direction yccw = direction.rotateYCCW();

		return direction == NORTH ? position.east() : position.offset(yccw);
	}

	@Override
	public ResourceLocation getBranchTemplateId(Random rand) {
		return rand.nextInt(5) == 0 ? LARGE_BRANCH : SMALL_BRANCH;
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.stream().sorted().iterator().next();

		return pos.north(5).west(5).up(height);
	}

}
