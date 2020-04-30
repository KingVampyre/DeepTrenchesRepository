package github.kingvampire.DeepTrenches.core.world.gen.trees;

import static github.kingvampire.DeepTrenches.api.enums.WoodType.BARSHROOKLE;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.STROOMEAN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CROLOOD_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.util.Direction.NORTH;
import static net.minecraft.util.Direction.WEST;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;
import static net.minecraft.util.Direction.Plane.HORIZONTAL;
import static net.minecraft.util.Mirror.FRONT_BACK;
import static net.minecraft.util.Rotation.CLOCKWISE_180;
import static net.minecraft.util.Rotation.CLOCKWISE_90;
import static net.minecraft.world.gen.feature.IFeatureConfig.NO_FEATURE_CONFIG;

import java.util.Random;
import java.util.Set;
import java.util.stream.StreamSupport;

import github.kingvampire.DeepTrenches.api.enums.WoodType;
import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateBranchTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.fungus.BarshrookleFungusFeature;
import github.kingvampire.DeepTrenches.core.world.gen.fungus.StroomeanFungusFeature;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public class CroloodTreeFeature extends TemplateBranchTreeFeature {

	private static final ResourceLocation ROOT_ONE = new ResourceLocation(MODID, "trees/crolood/root_one");
	private static final ResourceLocation ROOT_THREE = new ResourceLocation(MODID, "trees/crolood/root_three");
	private static final ResourceLocation ROOT_TWO = new ResourceLocation(MODID, "trees/crolood/root_two");
	private static final ResourceLocation TOP = new ResourceLocation(MODID, "trees/crolood/top");

	private boolean isSapling;

	public CroloodTreeFeature(boolean isSapling) {
		super(CROLOOD_LOG.getDefaultState(), TOP);

		this.isSapling = isSapling;
	}

	@Override
	public PlacementSettings getBranchSettings(Direction direction) {
		PlacementSettings settings = super.getSettings();

		if (direction.getAxis() == Z) {
			settings.setRotation(CLOCKWISE_90);

			return direction == NORTH ? settings.setMirror(FRONT_BACK) : settings;
		} else if (direction.getAxis() == X)
			return direction == WEST ? settings.setRotation(CLOCKWISE_180) : settings;

		return settings;
	}

	@Override
	public Template getBranchTemplate(ServerWorld worldIn) {
		TemplateManager manager = worldIn.getStructureTemplateManager();

		if (worldIn.rand.nextBoolean())
			return manager.getTemplate(ROOT_THREE);
		else if (worldIn.rand.nextBoolean())
			return manager.getTemplate(ROOT_TWO);

		return manager.getTemplate(ROOT_ONE);
	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {
		return 8 + server.rand.nextInt(5);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.stream().sorted().iterator().next();

		return pos.north(8).west(9).up(height);
	}

	@Override
	public boolean placeBranches(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {

		return StreamSupport.stream(HORIZONTAL.spliterator(), false).allMatch(direction -> {
			Template root = this.getBranchTemplate(worldIn);
			BlockPos size = root.getSize();

			BlockPos offset = position.down(size.getY()).offset(direction);
			PlacementSettings settings = this.getBranchSettings(direction);

			return root.addBlocksToWorld(worldIn, offset, settings, 2);
		});
	}

	@Override
	public boolean placeTrunk(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {
		ChunkGenerator<?> generator = worldIn.getChunkProvider().getChunkGenerator();
		Random rand = worldIn.getRandom();

		if (!this.isSapling && rand.nextInt(100) < 89) {

			WoodType woodType = rand.nextInt(89) < 46 ? BARSHROOKLE : STROOMEAN;
			ModThickTree tree = (ModThickTree) woodType.getTree();

			Direction direction = HORIZONTAL.random(rand);

			if (woodType == BARSHROOKLE) {
				TemplateTreeFeature feature = new BarshrookleFungusFeature(direction, tree, false);
				BlockPos pos = position.down(6).offset(direction, 6);

				IFluidState ifluidState = worldIn.getFluidState(pos);

				if (ifluidState.getFluid() == WATER)
					feature.place(worldIn, generator, rand, pos, NO_FEATURE_CONFIG);

			} else {
				TemplateTreeFeature feature = new StroomeanFungusFeature(direction, tree, false);
				BlockPos pos = position.down(9).offset(direction.getOpposite(), 5);

				IFluidState ifluidState = worldIn.getFluidState(pos);

				if (ifluidState.getFluid() == WATER)
					feature.place(worldIn, generator, rand, pos, NO_FEATURE_CONFIG);
			}
		}

		return super.placeTrunk(set, worldIn, position, height);
	}

}
