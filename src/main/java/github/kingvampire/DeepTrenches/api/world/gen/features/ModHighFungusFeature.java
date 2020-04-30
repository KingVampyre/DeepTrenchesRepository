package github.kingvampire.DeepTrenches.api.world.gen.features;

import java.util.Set;

import github.kingvampire.DeepTrenches.api.world.gen.trees.ModThickTree;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public abstract class ModHighFungusFeature extends ModFungusFeature {

	private Direction direction;
	private ResourceLocation trunkId;

	public ModHighFungusFeature(Direction direction, BlockState steam, ModThickTree thickTree) {
		super(direction.getAxis(), steam, thickTree);

		this.direction = direction;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public ResourceLocation getTrunkId() {
		return this.trunkId;
	}

	public abstract BlockPos getTrunkOffset(ServerWorld worldIn, Set<BlockPos> set, Template template, int i);

	public abstract PlacementSettings getTrunkSettings(Direction direction);

	@Override
	public boolean placeTrunk(Set<BlockPos> set, ServerWorld worldIn, BlockPos position, int height) {
		TemplateManager manager = worldIn.getStructureTemplateManager();
		ResourceLocation baseId = this.getBaseId();

		if (baseId != null && this.trunkId != null) {
			Template base = manager.getTemplate(baseId);
			Template trunk = manager.getTemplate(this.trunkId);

			BlockPos baseOffset = this.getBaseOffset(worldIn, set);
			BlockPos size = trunk.getSize();
			int times = height / size.getY();

			for (int i = 0; i < times; i++) {
				BlockPos offset = this.getTrunkOffset(worldIn, set, trunk, i);
				PlacementSettings settings = this.getTrunkSettings(this.direction);

				trunk.addBlocksToWorld(worldIn, offset, settings);
			}

			return base.addBlocksToWorld(worldIn, baseOffset, this.getSettings(), 2);
		}

		return false;
	}

	public void setTrunkId(ResourceLocation trunkId) {
		this.trunkId = trunkId;
	}

}
