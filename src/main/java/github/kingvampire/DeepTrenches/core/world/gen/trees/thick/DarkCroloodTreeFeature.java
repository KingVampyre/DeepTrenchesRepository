package github.kingvampire.DeepTrenches.core.world.gen.trees.thick;

import static github.kingvampire.DeepTrenches.api.enums.WoodType.BARSHROOKLE;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.STROOMEAN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.DARK_CROLOOD_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Plane.HORIZONTAL;
import static net.minecraft.util.Mirror.FRONT_BACK;
import static net.minecraft.util.Rotation.CLOCKWISE_90;
import static net.minecraft.world.gen.feature.IFeatureConfig.NO_FEATURE_CONFIG;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import github.kingvampire.DeepTrenches.api.enums.WoodType;
import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateBranchThickTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import github.kingvampire.DeepTrenches.core.world.gen.fungus.BarshrookleFungusFeature;
import github.kingvampire.DeepTrenches.core.world.gen.fungus.StroomeanFungusFeature;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public class DarkCroloodTreeFeature extends TemplateBranchThickTreeFeature {

	private static final ResourceLocation ROOT_ONE = new ResourceLocation(MODID, "trees/dark_crolood/root_one");
	private static final ResourceLocation ROOT_THREE = new ResourceLocation(MODID, "trees/dark_crolood/root_three");
	private static final ResourceLocation ROOT_TWO = new ResourceLocation(MODID, "trees/dark_crolood/root_two");
	private static final ResourceLocation TOP = new ResourceLocation(MODID, "trees/dark_crolood/top");

	private Axis axis;
	private boolean isSapling;

	public DarkCroloodTreeFeature(Axis axis, ModThickTree thickTree, boolean isSapling) {
		super(DARK_CROLOOD_LOG.getDefaultState(), thickTree);

		this.axis = axis;
		this.isSapling = isSapling;

		this.setTemplateId(TOP);
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
	public ResourceLocation getBranchTemplateId(Random rand) {

		if (rand.nextBoolean())
			return ROOT_THREE;
		else if (rand.nextBoolean())
			return ROOT_TWO;

		return ROOT_ONE;
	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {
		return 1 + server.rand.nextInt(5);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.stream().sorted().iterator().next().up(height);

		if (this.axis == X)
			return pos.south(10).east(13);

		return pos.north(12).west(9);
	}

	@Override
	public PlacementSettings getSettings() {
		PlacementSettings settings = super.getSettings();

		return this.axis == X ? settings.setRotation(CLOCKWISE_90).setMirror(FRONT_BACK) : settings;
	}

	@Override
	public boolean placeBranches(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {

		TemplateManager manager = worldIn.getStructureTemplateManager();
		Iterator<Direction> iterator = HORIZONTAL.iterator();

		return set.stream().sorted().allMatch(pos -> {
			ResourceLocation id = this.getBranchTemplateId(worldIn.rand);
			Template template = manager.getTemplate(id);

			Direction direction = iterator.next();
			int y = template.getSize().getY();

			BlockPos offset = this.getBranchOffset(worldIn, set, template, position, direction, y);
			PlacementSettings settings = this.getBranchSettings(direction);

			return template.addBlocksToWorld(worldIn, offset, settings, 2);
		});
	}

	@Override
	public BlockPos getBranchOffset(ServerWorld server, Set<BlockPos> positions, Template template, BlockPos position,
			Direction direction, int height) {

		return position.down(height).offset(direction);
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
				BlockPos pos = position.down(13).offset(direction, 10);

				IFluidState ifluidState = worldIn.getFluidState(pos);

				if (ifluidState.getFluid() == WATER)
					feature.place(worldIn, generator, rand, pos, NO_FEATURE_CONFIG);

			} else {
				TemplateTreeFeature feature = new StroomeanFungusFeature(direction, tree, true);
				BlockPos pos = position.down(16).offset(direction.getOpposite(), 9);

				IFluidState ifluidState = worldIn.getFluidState(pos);

				if (ifluidState.getFluid() == WATER)
					feature.place(worldIn, generator, rand, pos, NO_FEATURE_CONFIG);
			}
		}

		return super.placeTrunk(set, worldIn, position, height);
	}

}
