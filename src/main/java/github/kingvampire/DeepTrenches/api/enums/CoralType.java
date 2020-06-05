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
    BLACKGREEN_TREE,
    BROCCOLI,
    BUBBLEGUM,
    CABBAGE_TREE,
    FLOWERTUBE,
    GARNET_SPIRAL,
    GLOW_FOREST(10),
    GLOWTONGUE_TUBE(7),
    IVORY,
    LIME_BRAIN,
    LOPHELIA,
    PIPE_ORGAN,
    RED_TREE,
    SEAFAN,
    STRAIGHT_WILLOW,
    SUNRISE,
    TABLE,
    THIN_BLADE,
    TRUMPETEAR(5);

    private int light;
    
    private CoralType() {
	this(0);
    }
    
    private CoralType(int light) {
	this.light = light;
    }
    
    public Block coral(Block block) {
	Properties props = Properties.from(TUBE_CORAL).lightValue(this.light);
	Block coral = this == GLOW_FOREST ? new DoubleCoralBlock(block, props) : new ModCoralPlantBlock(block, props);

	return coral.setRegistryName(new ResourceLocation(MODID, this + "_coral"));
    }

    public Block coralBlock(Block block) {
	Block coral = new CoralBlock(block, Properties.from(TUBE_CORAL_BLOCK).lightValue(this.light));

	return coral.setRegistryName(new ResourceLocation(MODID, this + "_coral_block"));
    }

    public Block deadBlock() {
	Block block = new Block(Properties.from(DEAD_TUBE_CORAL_BLOCK).lightValue(this.light));

	return block.setRegistryName(new ResourceLocation(MODID, "dead_" + this + "_coral_block"));
    }

    public Block deadCoral() {
	Properties props = Properties.from(DEAD_TUBE_CORAL).lightValue(this.light);
	Block block = this == GLOW_FOREST ? new DeadDoubleCoralBlock(props) : new ModDeadCoralPlantBlock(props);

	return block.setRegistryName(new ResourceLocation(MODID, "dead_" + this + "_coral"));
    }

    public Block deadFan() {
	Block block = new ModDeadCoralFanBlock(Properties.from(DEAD_TUBE_CORAL_FAN).lightValue(this.light));

	return block.setRegistryName(new ResourceLocation(MODID, "dead_" + this + "_coral_fan"));
    }

    public Block deadTentacles() {
	Block tentacles = new Block(Properties.from(DEAD_TUBE_CORAL).lightValue(this.light));

	return tentacles.setRegistryName(new ResourceLocation(MODID, "dead_" + this + "_tentacles"));
    }

    public Block fan(Block block) {
	Block fan = new ModCoralFanBlock(block, Properties.from(TUBE_CORAL_FAN).lightValue(this.light));

	return fan.setRegistryName(new ResourceLocation(MODID, this + "_coral_fan"));
    }

    @Override
    public String getName() {
	return this.name().toLowerCase();
    }

    public Block tentacles(Block block) {
	Block tentacles = new ModCoralPlantBlock(block, Properties.from(TUBE_CORAL).lightValue(this.light));

	return tentacles.setRegistryName(new ResourceLocation(MODID, this + "_tentacles"));
    }

    @Override
    public String toString() {
	return this.getName();
    }

}
