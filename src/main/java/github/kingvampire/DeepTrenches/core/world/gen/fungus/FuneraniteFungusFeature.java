package github.kingvampire.DeepTrenches.core.world.gen.fungus;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUNERANITE_LOG;
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

public class FuneraniteFungusFeature extends ModHighFungusFeature {

	private static final ResourceLocation BASE = new ResourceLocation(MODID, "fungus/funeranite/base");
	private static final ResourceLocation TOP = new ResourceLocation(MODID, "fungus/funeranite/top");
	private static final ResourceLocation TRUNK = new ResourceLocation(MODID, "fungus/funeranite/trunk");

	public FuneraniteFungusFeature(Direction direction, ModThickTree thickTree) {
		super(direction, FUNERANITE_LOG.getDefaultState(), thickTree);

		this.setBaseId(BASE);
		this.setTemplateId(TOP);
		this.setTrunkId(TRUNK);
	}

	@Override
	public BlockPos getBaseOffset(ServerWorld server, Set<BlockPos> positions) {
		BlockPos pos = positions.stream().sorted().iterator().next();

		Direction direction = this.getDirection();
		Direction yccw = direction.rotateYCCW();

		BlockPos offset = pos.offset(direction);

		if (direction == EAST)
			return offset.east();

		BlockPos yccwOffset = offset.offset(yccw);

		if (direction == SOUTH)
			return yccwOffset.south();

		return yccwOffset;
	}

	@Override
	public int getHeight(ServerWorld server, Set<BlockPos> positions) {
		Stream<Integer> stream = IntStream.rangeClosed(0, 8).filter(num -> num % 4 == 0).boxed();
		List<Integer> integers = stream.collect(Collectors.toList());

		Collections.shuffle(integers);

		return integers.get(0);
	}

	@Override
	public BlockPos getOffset(ServerWorld server, Set<BlockPos> positions, Template template, int height) {
		BlockPos pos = super.getOffset(server, positions, template, height);

		Direction direction = this.getDirection();
		Direction yccw = direction.rotateYCCW();

		int i = height / 4;

		if (direction == EAST)
			return pos.north(3 + i).east(3 + i);
		else if (direction == SOUTH)
			return pos.south(3 + i).east(4 + i);

		return pos.offset(direction, 2 + i).offset(yccw, 4 + i);
	}

	@Override
	public BlockPos getTrunkOffset(ServerWorld worldIn, Set<BlockPos> set, Template template, int i) {
		Direction direction = this.getDirection();
		Direction yccw = direction.rotateYCCW();
		int height = i > 0 ? i * 4 : 0;

		BlockPos pos = super.getOffset(worldIn, set, template, height);
		BlockPos offset = pos.offset(yccw, i + 1).offset(direction, i);

		if (direction == SOUTH)
			return offset.south();
		if (direction == EAST)
			return offset.east().south();

		return offset;
	}

	@Override
	public PlacementSettings getTrunkSettings(Direction direction) {
		return this.getSettings();
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

}
