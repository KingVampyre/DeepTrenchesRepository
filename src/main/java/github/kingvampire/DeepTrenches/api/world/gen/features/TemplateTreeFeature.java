package github.kingvampire.DeepTrenches.api.world.gen.features;

import static net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor.AIR;

import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public class TemplateTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {

	private BlockState log;
	private int maxHeight;
	private int minHeight;
	private ResourceLocation templateId;

	public TemplateTreeFeature(BlockState log, int minHeight, int maxHeight, ResourceLocation templateId) {
		super(NoFeatureConfig::deserialize, true);

		this.log = log;
		this.templateId = templateId;

		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
	}

	public TemplateTreeFeature(BlockState log, ResourceLocation templateId) {
		this(log, 0, 0, templateId);
	}

	public int getHeight(ServerWorld server, Set<BlockPos> positions) {
		int bound = this.maxHeight - this.minHeight + 1;

		return this.minHeight + server.rand.nextInt(bound);
	}

	public BlockState getLog() {
		return this.log;
	}

	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		return positions.iterator().next();
	}

	public Set<BlockPos> getSaplings(World world, BlockPos pos) {
		return Sets.newHashSet(pos);
	}

	public PlacementSettings getSettings() {
		PlacementSettings settings = new PlacementSettings();

		return settings.addProcessor(AIR);
	}

	public ResourceLocation getTemplateId(TemplateManager manager, Random random) {
		return this.templateId;
	}

	@Override
	protected boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position,
			MutableBoundingBox box) {

		ServerWorld server = (ServerWorld) worldIn;
		TemplateManager manager = server.getStructureTemplateManager();
		ResourceLocation id = this.getTemplateId(manager, rand);

		if (id == null)
			return false;

		Template template = manager.getTemplate(id);

		PlacementSettings settings = this.getSettings();
		Set<BlockPos> set = this.getSaplings(server, position);

		int height = this.getHeight(server, set);
		BlockPos offset = this.getOffset(server, set, template, height);

		if (set.isEmpty())
			return false;

		return this.placeTrunk(set, server, position, height) && template.addBlocksToWorld(server, offset, settings, 2);
	}

	public boolean placeTrunk(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {

		for (int i = 0; i < height; i++) {
			BlockPos pos = position.up(i);

			worldIn.setBlockState(pos, this.log);
		}

		return true;
	}

	public void setLog(BlockState log) {
		this.log = log;
	}

	public void setTemplateId(ResourceLocation templateId) {
		this.templateId = templateId;
	}

}
