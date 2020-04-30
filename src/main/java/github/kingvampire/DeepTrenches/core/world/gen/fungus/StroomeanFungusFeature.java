package github.kingvampire.DeepTrenches.core.world.gen.fungus;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STROOMEAN_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.util.Direction.EAST;
import static net.minecraft.util.Direction.NORTH;
import static net.minecraft.util.Direction.WEST;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;
import static net.minecraft.util.Rotation.CLOCKWISE_180;
import static net.minecraft.util.Rotation.CLOCKWISE_90;
import static net.minecraft.util.Rotation.COUNTERCLOCKWISE_90;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import github.kingvampire.DeepTrenches.api.world.gen.features.ModHighFungusFeature;
import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class StroomeanFungusFeature extends ModHighFungusFeature {

	private static final ResourceLocation BASE = new ResourceLocation(MODID, "fungus/stroomean/base");
	private static final ResourceLocation TOP = new ResourceLocation(MODID, "fungus/stroomean/top");
	private static final ResourceLocation TRUNK = new ResourceLocation(MODID, "fungus/stroomean/trunk");

	private boolean isSapling;

	public StroomeanFungusFeature(Direction direction, ModThickTree thickTree, boolean isSapling) {
		super(direction, STROOMEAN_LOG.getDefaultState(), thickTree);

		this.setBaseId(BASE);
		this.setTemplateId(TOP);
		this.setTrunkId(TRUNK);

		this.isSapling = isSapling;
	}

	@Override
	public BlockPos getBaseOffset(ServerWorld server, Set<BlockPos> positions) {
		BlockPos pos = positions.stream().sorted().iterator().next();

		Direction direction = this.getDirection();
		Direction yccw = direction.rotateYCCW();
		int n = direction.getAxis() == X ? 2 : 3;

		return pos.offset(direction, 3).offset(yccw, n);
	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {
		Stream<Integer> stream = IntStream
				.rangeClosed(0, this.isSapling ? 8 : 6)
				.filter(num -> num % 2 == 0)
				.filter(this.isSapling ? n -> n != 2 && n != 6 : n -> true).boxed();

		List<Integer> integers = stream.collect(Collectors.toList());

		Collections.shuffle(integers);

		return integers.get(0);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = super.getOffset(server, positions, template, height - 2);

		Direction direction = this.getDirection();
		Direction opposite = direction.getOpposite();
		Direction yccw = direction.rotateYCCW();

		int i = height > 0 ? height / 2 : (height - 2) / 2;
		int n = i > 0 ? i : 0;

		BlockPos offset = pos.offset(opposite, i + n);

		if (direction == WEST)
			return offset.offset(yccw, 3);

		return offset.offset(yccw, direction == EAST ? 3 : 4);
	}

	@Override
	public PlacementSettings getSettings() {
		PlacementSettings settings = super.getSettings();
		Direction direction = this.getDirection();

		if (direction.getAxis() == Z)
			return direction == NORTH ? settings : settings.setRotation(CLOCKWISE_180);
		else if (direction.getAxis() == X)
			return direction == WEST ? settings.setRotation(COUNTERCLOCKWISE_90) : settings.setRotation(CLOCKWISE_90);

		return settings;
	}

	@Override
	public BlockPos getTrunkOffset(ServerWorld worldIn, Set<BlockPos> set, Template template, int i) {
		BlockPos pos = super.getOffset(worldIn, set, template, -2);
		
		Direction direction = this.getDirection();
		Direction opposite = direction.getOpposite();

		int n = i > 0 ? i : 0;

		BlockPos offset = pos.offset(opposite, i + n).up(i + n);

		if (direction.getAxis() == Z)
			return direction == NORTH ? offset.west() : offset.east();

		return offset;
	}

	@Override
	public PlacementSettings getTrunkSettings(Direction direction) {
		return this.getSettings();
	}

}
