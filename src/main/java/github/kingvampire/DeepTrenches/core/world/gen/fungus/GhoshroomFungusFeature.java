package github.kingvampire.DeepTrenches.core.world.gen.fungus;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.GHOSHROOM_LOG;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.util.Direction.EAST;
import static net.minecraft.util.Direction.NORTH;
import static net.minecraft.util.Direction.SOUTH;
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

public class GhoshroomFungusFeature extends ModHighFungusFeature {

	private static final ResourceLocation BASE = new ResourceLocation(MODID, "fungus/ghoshroom/base");
	private static final ResourceLocation TOP = new ResourceLocation(MODID, "fungus/ghoshroom/top");
	private static final ResourceLocation TRUNK = new ResourceLocation(MODID, "fungus/ghoshroom/trunk");

	public GhoshroomFungusFeature(Direction direction, ModThickTree thickTree) {
		super(direction, GHOSHROOM_LOG.getDefaultState(), thickTree);

		this.setBaseId(BASE);
		this.setTemplateId(TOP);
		this.setTrunkId(TRUNK);
	}

	@Override
	public BlockPos getBaseOffset(ServerWorld server, Set<BlockPos> positions) {
		return positions.stream().sorted().iterator().next();
	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {
		Stream<Integer> stream = IntStream.rangeClosed(0, 6).filter(num -> num % 2 == 0).boxed();
		List<Integer> integers = stream.collect(Collectors.toList());

		Collections.shuffle(integers);

		return integers.get(0);
	}

	@Override
	public BlockPos getTrunkOffset(ServerWorld worldIn, Set<BlockPos> set, Template template, int i) {
		BlockPos pos = super.getOffset(worldIn, set, template, 0);
		Direction direction = this.getDirection();
		int n = i > 0 ? i : 0;

		BlockPos offset = pos.east().offset(direction, i + n).up(i + n);

		if (this.getDirection() == SOUTH)
			return offset.south();

		return offset;
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = super.getOffset(server, positions, template, height);
		Direction direction = this.getDirection();

		int i = height / 2;
		int n = i > 0 ? i : 0;

		if (direction == NORTH)
			return pos.north(i + n + 4).west(3);
		else if (direction == WEST)
			return pos.north(4).west(i + n + 3);
		else if (direction == SOUTH)
			return pos.south(i + n - 3).west(3);
		else if (direction == EAST)
			return pos.north(4).east(i + n - 3);

		return pos;
	}

	@Override
	public PlacementSettings getTrunkSettings(Direction direction) {
		PlacementSettings settings = super.getSettings();

		if (direction.getAxis() == Z)
			return direction == NORTH ? settings.setRotation(CLOCKWISE_180) : settings;
		else if (direction.getAxis() == X)
			return direction == WEST ? settings.setRotation(CLOCKWISE_90) : settings.setRotation(COUNTERCLOCKWISE_90);

		return settings;
	}

}
