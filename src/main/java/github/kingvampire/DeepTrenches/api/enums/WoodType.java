package github.kingvampire.DeepTrenches.api.enums;

import static github.kingvampire.DeepTrenches.core.init.ModMaterials.FUNGUS;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.block.Blocks.FLOWER_POT;
import static net.minecraft.block.Blocks.OAK_BUTTON;
import static net.minecraft.block.Blocks.OAK_DOOR;
import static net.minecraft.block.Blocks.OAK_FENCE;
import static net.minecraft.block.Blocks.OAK_FENCE_GATE;
import static net.minecraft.block.Blocks.OAK_LOG;
import static net.minecraft.block.Blocks.OAK_PLANKS;
import static net.minecraft.block.Blocks.OAK_PRESSURE_PLATE;
import static net.minecraft.block.Blocks.OAK_SAPLING;
import static net.minecraft.block.Blocks.OAK_SIGN;
import static net.minecraft.block.Blocks.OAK_SLAB;
import static net.minecraft.block.Blocks.OAK_STAIRS;
import static net.minecraft.block.Blocks.OAK_TRAPDOOR;
import static net.minecraft.block.Blocks.OAK_WALL_SIGN;
import static net.minecraft.block.Blocks.OAK_WOOD;
import static net.minecraft.block.Blocks.STRIPPED_OAK_LOG;
import static net.minecraft.block.Blocks.STRIPPED_OAK_WOOD;
import static net.minecraft.block.PressurePlateBlock.Sensitivity.EVERYTHING;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import github.kingvampire.DeepTrenches.api.world.gen.trees.ModTree;
import github.kingvampire.DeepTrenches.core.blocks.CapBlock;
import github.kingvampire.DeepTrenches.core.blocks.FungusBlock;
import github.kingvampire.DeepTrenches.core.blocks.StorceanSaplingBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModDoorBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModFenceBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModFenceGateBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModLeavesBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModLogBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModPlanksBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModPressurePlateBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModRotatedPillarBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModSaplingBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModSlabBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModStairsBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModStandingSignBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModTrapDoorBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModWallSignBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModWoodButtonBlock;
import github.kingvampire.DeepTrenches.core.blocks.trees.AlmondTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.AnameataTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.AqueanTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.BarshrookleTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.BlackBirchTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.CherryTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.CookPineTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.CroloodTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.DarkCroldoodTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.EbonyTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.FuchsitraTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.FuneraniteTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.GhoshroomTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.PeltogyneTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.PinCherryTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.PlumTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.PurfungaTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.SproomTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.StortreeanTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.StroomeanTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.SunriseFungusTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.TeakTree;
import github.kingvampire.DeepTrenches.core.blocks.trees.ThundercloudPlumTree;
import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.SoundType;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public enum WoodType implements IStringSerializable {
    ALMOND(AlmondTree::new),
    ANAMEATA(AnameataTree::new, false),
    AQUEAN(AqueanTree::new),
    BARSHROOKLE(BarshrookleTree::new, false),
    BLACK_BIRCH(BlackBirchTree::new),
    CHERRY(CherryTree::new),
    COOK_PINE(CookPineTree::new),
    CROLOOD(CroloodTree::new),
    DARK_CROLOOD(DarkCroldoodTree::new, 6),
    EBONY(EbonyTree::new),
    FUCHSITRA(FuchsitraTree::new),
    FUNERANITE(FuneraniteTree::new, false),
    GHOSHROOM(GhoshroomTree::new, false, 6),
    PELTOGYNE(PeltogyneTree::new),
    PIN_CHERRY(PinCherryTree::new),
    PLUM(PlumTree::new),
    PURFUNGA(PurfungaTree::new, false),
    SPROOM(SproomTree::new, 6),
    STORTREEAN(StortreeanTree::new, 6),
    STROOMEAN(StroomeanTree::new, false),
    SUNRISE_FUNGUS(SunriseFungusTree::new, false, 6),
    TEAK(TeakTree::new),
    THUNDERCLOUD_PLUM(ThundercloudPlumTree::new);

    @Nullable
    public static WoodType getType(String name) {

	for (WoodType type : WoodType.values())
	    if (type.getName().equals(name))
		return type;

	return null;
    }

    private boolean hasLeaves;
    private int light;
    private ModTree tree;

    WoodType(Supplier<ModTree> tree) {
	this(tree, true, 0);
    }

    WoodType(Supplier<ModTree> tree, boolean hasLeaves) {
	this(tree, hasLeaves, 0);
    }

    WoodType(Supplier<ModTree> tree, boolean hasLeaves, int light) {
	this.hasLeaves = hasLeaves;
	this.light = light;
	this.tree = tree.get();
    }

    WoodType(Supplier<ModTree> tree, int light) {
	this(tree, true, light);
    }

    public Block button(int light) {
	Block block = new ModWoodButtonBlock(Properties.from(OAK_BUTTON).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, this + "_button"));
    }

    public Block cap(int light) {
	Properties props = Properties.create(FUNGUS).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.WOOD)
		.lightValue(light);

	Block block = new CapBlock(props);

	return block.setRegistryName(new ResourceLocation(MODID, this + "_cap"));
    }

    public Block door(int light) {
	Block block = new ModDoorBlock(Properties.from(OAK_DOOR).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, this + "_door"));
    }

    public Block fence(int light) {
	Block block = new ModFenceBlock(Properties.from(OAK_FENCE).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, this + "_fence"));
    }

    public Block fenceGate(int light) {
	Block block = new ModFenceGateBlock(Properties.from(OAK_FENCE_GATE).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, this + "_fence_gate"));
    }

    public int getLight() {
	return this.light;
    }

    @Override
    public String getName() {
	return this.name().toLowerCase();
    }

    public ModTree getTree() {
	return this.tree;
    }

    public boolean hasLeaves() {
	return this.hasLeaves;
    }

    public Block leaves() {
	Block block = new ModLeavesBlock();

	return block.setRegistryName(new ResourceLocation(MODID, this + "_leaves"));
    }

    public Block log(int light) {
	Block block = new ModLogBlock(Properties.from(OAK_LOG).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, this + "_log"));
    }

    public Block planks(int light) {
	Block block = new ModPlanksBlock(Properties.from(OAK_PLANKS).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, this + "_planks"));
    }

    @SuppressWarnings("deprecation")
    public Block pot(Block sapling) {
	Properties properties = Properties.from(FLOWER_POT);
	Block block = new FlowerPotBlock(sapling, properties);

	return block.setRegistryName(new ResourceLocation(MODID, "potted_" + this + "_sapling"));
    }

    public Block pressurePlate(int light) {
	Block block = new ModPressurePlateBlock(EVERYTHING, Properties.from(OAK_PRESSURE_PLATE).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, this + "_pressure_plate"));
    }

    public Block sapling(int light) {
	Properties properties = Properties.from(OAK_SAPLING).lightValue(light);

	if (SPROOM == this || STORTREEAN == this) {
	    Block block = new StorceanSaplingBlock(this.tree, properties);

	    return block.setRegistryName(new ResourceLocation(MODID, this + "_sapling"));
	}

	Properties fungus = Block.Properties.create(FUNGUS)
		.doesNotBlockMovement()
		.tickRandomly()
		.hardnessAndResistance(0)
		.sound(SoundType.PLANT)
		.lightValue(light);

	if (PURFUNGA == this || SUNRISE_FUNGUS == this) {
	    Block block = new FungusBlock(this.tree, fungus, false);

	    return block.setRegistryName(new ResourceLocation(MODID, this + "_sapling"));
	}

	if (BARSHROOKLE == this || STROOMEAN == this) {
	    Block block = new FungusBlock(this.tree, fungus, true);

	    return block.setRegistryName(new ResourceLocation(MODID, this + "_sapling"));
	}

	Block block = new ModSaplingBlock(this.tree, fungus);

	return block.setRegistryName(new ResourceLocation(MODID, this + "_sapling"));
    }

    public Block sign(int light) {
	Block block = new ModStandingSignBlock(Properties.from(OAK_SIGN).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, this + "_sign"));
    }

    public Block slabs(int light) {
	Block block = new ModSlabBlock(Properties.from(OAK_SLAB).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, this + "_slab"));
    }

    public Block stairs(Block planks, int light) {
	Block block = new ModStairsBlock(planks::getDefaultState, Properties.from(OAK_STAIRS).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, this + "_stairs"));
    }

    public Block strippedLog(int light) {
	Block block = new ModLogBlock(Properties.from(STRIPPED_OAK_LOG).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, "stripped_" + this + "_log"));
    }

    public Block strippedWood(int light) {
	Block block = new ModRotatedPillarBlock(Properties.from(STRIPPED_OAK_WOOD).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, "stripped_" + this + "_wood"));
    }

    @Override
    public String toString() {
	return this.getName();
    }

    public Block trapdoor(int light) {
	Block block = new ModTrapDoorBlock(Properties.from(OAK_TRAPDOOR).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, this + "_trapdoor"));
    }

    public Block wallSign(int light) {
	Block block = new ModWallSignBlock(Properties.from(OAK_WALL_SIGN).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, this + "_wall_sign"));
    }

    public Block wood(int light) {
	Block block = new ModRotatedPillarBlock(Properties.from(OAK_WOOD).lightValue(light));

	return block.setRegistryName(new ResourceLocation(MODID, this + "_wood"));
    }

}