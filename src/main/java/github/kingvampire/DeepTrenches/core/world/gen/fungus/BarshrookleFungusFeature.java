package github.kingvampire.DeepTrenches.core.world.gen.fungus;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BARSHROOKLE_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.util.Direction.NORTH;
import static net.minecraft.util.Direction.WEST;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;
import static net.minecraft.util.Mirror.FRONT_BACK;
import static net.minecraft.util.Mirror.LEFT_RIGHT;
import static net.minecraft.util.Rotation.CLOCKWISE_180;
import static net.minecraft.util.Rotation.CLOCKWISE_90;

import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;

import github.kingvampire.DeepTrenches.api.world.gen.features.StraightFungusFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public class BarshrookleFungusFeature extends StraightFungusFeature {

	private static final ResourceLocation BASE_GROUND = new ResourceLocation(MODID, "fungus/barshrookle/ground_base");
	private static final ResourceLocation BASE_SIDE = new ResourceLocation(MODID, "fungus/barshrookle/side_base");
	private static final ResourceLocation TOP_ONE = new ResourceLocation(MODID, "fungus/barshrookle/top_one");
	private static final ResourceLocation TOP_TWO = new ResourceLocation(MODID, "fungus/barshrookle/top_two");

	private Direction direction;

	public BarshrookleFungusFeature(Direction direction, ModThickTree thickTree, boolean onGround) {
		super(direction.getAxis(), BARSHROOKLE_LOG.getDefaultState(), thickTree);

		this.direction = direction;

		this.setBaseId(onGround ? BASE_GROUND : BASE_SIDE);
	}

	@Override
	public BlockPos getBaseOffset(ServerWorld server, Set<BlockPos> positions) {
		BlockPos pos = positions.stream().sorted().iterator().next();
		Direction yccw = this.direction.rotateYCCW();

		if (this.getAxis() == Z)
			return pos.offset(this.direction, 2).offset(yccw, 2);

		return pos.offset(this.direction, 2).offset(yccw.getOpposite(), 2);
	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {
		return server.rand.nextInt(7);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = super.getOffset(server, positions, template, height).offset(this.direction, 2);

		TemplateManager manager = server.getStructureTemplateManager();
		Direction yccw = this.direction.rotateYCCW();

		if (manager.getTemplate(TOP_ONE) != template)
			return this.getAxis() == X ? pos.offset(yccw.getOpposite()) : pos.offset(yccw);

		return pos;
	}

	@Override
	public PlacementSettings getSettings() {
		PlacementSettings settings = super.getSettings();

		if (this.direction.getAxis() == X) {
			Mirror mirror = this.direction == WEST ? LEFT_RIGHT : FRONT_BACK;

			return settings.setRotation(CLOCKWISE_90).setMirror(mirror);

		} else if (this.direction.getAxis() == Z)
			return this.direction == NORTH ? settings : settings.setRotation(CLOCKWISE_180);

		return settings;
	}

	@Override
	public ResourceLocation getTemplateId(TemplateManager manager, Random random) {
		return random.nextBoolean() ? TOP_ONE : TOP_TWO;
	}

	@Override
	public boolean placeTrunk(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {
		return super.placeTrunk(Sets.newHashSet(position), worldIn, position, height);
	}

}
