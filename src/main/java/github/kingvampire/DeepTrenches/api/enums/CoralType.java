package github.kingvampire.DeepTrenches.api.enums;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.block.Blocks.DEAD_TUBE_CORAL;
import static net.minecraft.block.Blocks.DEAD_TUBE_CORAL_BLOCK;
import static net.minecraft.block.Blocks.DEAD_TUBE_CORAL_FAN;
import static net.minecraft.block.Blocks.TUBE_CORAL;
import static net.minecraft.block.Blocks.TUBE_CORAL_BLOCK;
import static net.minecraft.block.Blocks.TUBE_CORAL_FAN;

import github.kingvampire.DeepTrenches.core.blocks.DeadDoubleCoralBlock;
import github.kingvampire.DeepTrenches.core.blocks.DoubleCoralBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModCoralFanBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModCoralPlantBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModDeadCoralFanBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModDeadCoralPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.CoralBlock;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public enum CoralType implements IStringSerializable {
	BLACKGREEN_TREE, BUBBLEGUM, CABBAGE_TREE, FLOWERTUBE, GARNET_SPIRAL, GLOW_FOREST, GLOWTONGUE_TUBE, IVORY,
	LIME_BRAIN, LOPHELIA, PIPE_ORGAN, RED_TREE, SEAFAN, STRAIGHT_WILLOW, SUNRISE, TABLE, THIN_BLADE, TRUMPETEAR;

	public Block coral(Block block) {
		Properties props = Properties.from(TUBE_CORAL);
		Block coral = this == GLOW_FOREST ? new DoubleCoralBlock(block, props) : new ModCoralPlantBlock(block, props);

		return coral.setRegistryName(new ResourceLocation(MODID, this + "_coral"));
	}

	public Block coralBlock(Block block) {
		Block coral = new CoralBlock(block, Properties.from(TUBE_CORAL_BLOCK));

		return coral.setRegistryName(new ResourceLocation(MODID, this + "_coral_block"));
	}

	public Block deadBlock() {
		Block block = new Block(Properties.from(DEAD_TUBE_CORAL_BLOCK));

		return block.setRegistryName(new ResourceLocation(MODID, "dead_" + this + "_coral_block"));
	}

	public Block deadCoral() {
		Properties props = Properties.from(DEAD_TUBE_CORAL);
		Block block = this == GLOW_FOREST ? new DeadDoubleCoralBlock(props) : new ModDeadCoralPlantBlock(props);

		return block.setRegistryName(new ResourceLocation(MODID, "dead_" + this + "_coral"));
	}

	public Block deadFan() {
		Block block = new ModDeadCoralFanBlock(Properties.from(DEAD_TUBE_CORAL_FAN));

		return block.setRegistryName(new ResourceLocation(MODID, "dead_" + this + "_coral_fan"));
	}

	public Block deadTentacles() {
		Block tentacles = new Block(Properties.from(DEAD_TUBE_CORAL));

		return tentacles.setRegistryName(new ResourceLocation(MODID, "dead_" + this + "_tentacles"));
	}

	public Block fan(Block block) {
		Block fan = new ModCoralFanBlock(block, Properties.from(TUBE_CORAL_FAN));

		return fan.setRegistryName(new ResourceLocation(MODID, this + "_coral_fan"));
	}

	@Override
	public String getName() {
		return this.name().toLowerCase();
	}

	public Block tentacles(Block block) {
		Block tentacles = new ModCoralPlantBlock(block, Properties.from(TUBE_CORAL));

		return tentacles.setRegistryName(new ResourceLocation(MODID, this + "_tentacles"));
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
