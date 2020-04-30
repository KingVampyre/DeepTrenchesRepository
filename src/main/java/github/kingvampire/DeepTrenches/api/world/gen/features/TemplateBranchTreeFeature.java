package github.kingvampire.DeepTrenches.api.world.gen.features;

import static net.minecraft.util.Direction.NORTH;
import static net.minecraft.util.Direction.WEST;
import static net.minecraft.util.Direction.Axis.X;
import static net.minecraft.util.Direction.Axis.Z;
import static net.minecraft.util.Mirror.FRONT_BACK;
import static net.minecraft.util.Rotation.CLOCKWISE_180;
import static net.minecraft.util.Rotation.CLOCKWISE_90;

import java.util.Set;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public abstract class TemplateBranchTreeFeature extends TemplateTreeFeature {

	public TemplateBranchTreeFeature(BlockState log, ResourceLocation templateId) {
		super(log, 0, 0, templateId);
	}

	public abstract Template getBranchTemplate(ServerWorld worldIn);

	public PlacementSettings getBranchSettings(Direction direction) {
		PlacementSettings settings = super.getSettings();

		if (direction.getAxis() == Z) {
			settings.setRotation(CLOCKWISE_90);

			return direction == NORTH ? settings.setMirror(FRONT_BACK) : settings;
		} else if (direction.getAxis() == X)
			return direction == WEST ? settings.setRotation(CLOCKWISE_180) : settings;

		return settings;
	}

	public abstract boolean placeBranches(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height);

	@Override
	public boolean placeTrunk(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {
		return super.placeTrunk(set, worldIn, position, height) && this.placeBranches(set, worldIn, position, height);
	}

}
