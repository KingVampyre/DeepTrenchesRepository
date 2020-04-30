package github.kingvampire.DeepTrenches.core.world.gen.trees.thick;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.COOK_PINE_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import github.kingvampire.DeepTrenches.api.world.gen.features.TemplateThickTreeFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public class CookPineTreeFeature extends TemplateThickTreeFeature {

	private static final ResourceLocation LARGE_CIRCLE = new ResourceLocation(MODID, "trees/cook_pine/large_circle");
	private static final ResourceLocation MEDIUM_CIRCLE = new ResourceLocation(MODID, "trees/cook_pine/medium_circle");
	private static final ResourceLocation SMALL_CIRCLE = new ResourceLocation(MODID, "trees/cook_pine/small_circle");
	private static final ResourceLocation TOP = new ResourceLocation(MODID, "trees/cook_pine/top");

	private Direction direction;

	public CookPineTreeFeature(ModThickTree thickTree, Direction direction) {
		super(COOK_PINE_LOG.getDefaultState(), thickTree);

		this.direction = direction;

		this.setTemplateId(TOP);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = positions.stream().sorted().iterator().next();
		BlockPos size = template.getSize();

		int x = size.getX() / 2;
		int z = size.getZ() / 2;

		return pos.north(z).west(x).south().east().up(height);
	}

	private List<Integer> getPlatforms(Random random) {
		int limit = 6 + random.nextInt(5);

		return IntStream.generate(() -> 2).limit(limit).boxed().collect(Collectors.toList());
	}

	public ResourceLocation getCircleTemplateId(TemplateManager manager, Random random) {

		if (random.nextInt(9) == 0)
			return LARGE_CIRCLE;
		else if (random.nextBoolean())
			return MEDIUM_CIRCLE;

		return SMALL_CIRCLE;
	}

	private List<Template> getTemplates(ServerWorld worldIn, int size) {
		TemplateManager manager = worldIn.getStructureTemplateManager();

		return Stream.generate(() -> this.getCircleTemplateId(manager, worldIn.rand)).limit(size)
				.map(manager::getTemplate).sorted((temp, temp1) -> {
					BlockPos tempSize = temp.getSize();
					BlockPos tempSize1 = temp1.getSize();

					return -tempSize.compareTo(tempSize1);
				}).collect(Collectors.toList());
	}

	@Override
	protected boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position,
			MutableBoundingBox box) {

		ServerWorld server = (ServerWorld) worldIn;

		Set<BlockPos> set = this.getSaplings(server, position);
		PlacementSettings settings = this.getSettings();
		int trunk = this.getHeight(server, set);

		List<Integer> platforms = this.getPlatforms(rand);
		List<Template> templates = this.getTemplates(server, platforms.size());

		for (int i = 0; i < platforms.size(); i++) {
			Integer platform = platforms.get(i);
			Template template = templates.get(i);

			int y = template.getSize().getY();
			BlockPos offset = this.getOffset(server, set, template, trunk);

			for (int j = 1; j <= platform; j++) {
				BlockPos pos = offset.offset(this.direction, i).up(j * y + i * platform * y - y);

				template.addBlocksToWorld(server, pos, settings);
			}
		}

		TemplateManager manager = server.getStructureTemplateManager();
		ResourceLocation id = this.getTemplateId(manager, rand);

		int height = platforms.stream().mapToInt(i -> i * 2).sum();
		Template template = manager.getTemplate(id);

		BlockPos offset = this.getOffset(server, set, template, height + trunk);
		BlockPos place = offset.offset(this.direction, platforms.size() - 1);

		return template.addBlocksToWorld(server, place, settings, 2) && this.placeTrunk(set, server, position, trunk);
	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {
		return 6;
	}

}
