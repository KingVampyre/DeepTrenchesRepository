package github.kingvampire.DeepTrenches.core.util;

import static github.kingvampire.DeepTrenches.core.init.ModBlocks.*;
import static net.minecraft.block.Blocks.DEAD_TUBE_CORAL;
import static net.minecraft.block.Blocks.DEAD_TUBE_CORAL_BLOCK;
import static net.minecraft.block.Blocks.DEAD_TUBE_CORAL_FAN;
import static net.minecraft.block.Blocks.TUBE_CORAL;
import static net.minecraft.block.Blocks.TUBE_CORAL_BLOCK;
import static net.minecraft.block.Blocks.TUBE_CORAL_FAN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import github.kingvampire.DeepTrenches.core.blocks.CoralFanBlock;
import github.kingvampire.DeepTrenches.core.blocks.CoralPlantBlock;
import github.kingvampire.DeepTrenches.core.blocks.DeadCoralFanBlock;
import github.kingvampire.DeepTrenches.core.blocks.DeadCoralPlantBlock;
import github.kingvampire.DeepTrenches.core.blocks.DeadDoubleCoralBlock;
import github.kingvampire.DeepTrenches.core.blocks.DoubleCoralBlock;
import github.kingvampire.DeepTrenches.core.init.ItemGroups;
import net.minecraft.block.Block;
import net.minecraft.block.CoralBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD)
public class RegistryHandler {

	@SubscribeEvent
	public static void registerBlocks(Register<Block> event) {
		List<Block> blocks = new ArrayList<>();

		Block.Properties coralBlockProps = Block.Properties.from(TUBE_CORAL_BLOCK);
		Block.Properties deadCoralBlockProps = Block.Properties.from(DEAD_TUBE_CORAL_BLOCK);
		Block.Properties coralProps = Block.Properties.from(TUBE_CORAL);
		Block.Properties deadCoralProps = Block.Properties.from(DEAD_TUBE_CORAL);
		Block.Properties fanProps = Block.Properties.from(TUBE_CORAL_FAN);
		Block.Properties deadFanProps = Block.Properties.from(DEAD_TUBE_CORAL_FAN);

		String[] corals = new String[] { "blackgreen_tree", "bubblegum", "cabbage_tree", "flowertube", "garnet_spiral",
				"glow_forest", "glowtongue_tube", "ivory", "lime_brain", "lophelia", "pipe_organ", "red_tree", "seafan",
				"straight_willow", "sunrise", "table", "thin_blade", "trumpetear" };

		Arrays.asList(corals).forEach(coral -> {
			Block deadCoralBlock = new Block(deadCoralBlockProps);
			Block coralBlock = new CoralBlock(deadCoralBlock, coralBlockProps);

			Block deadCoralPlant = coral.equals("glow_forest") ? new DeadDoubleCoralBlock(deadCoralProps)
					: new DeadCoralPlantBlock(deadCoralProps);

			Block coralPlant = coral.equals("glow_forest") ? new DoubleCoralBlock(deadCoralPlant, coralProps)
					: new CoralPlantBlock(deadCoralPlant, coralProps);

			Block deadFan = new DeadCoralFanBlock(deadFanProps);
			Block fan = new CoralFanBlock(deadFan, fanProps);

			blocks.add(Resources.attach(coral + "_coral_block", coralBlock));
			blocks.add(Resources.attach("dead_" + coral + "_coral_block", deadCoralBlock));
			blocks.add(Resources.attach(coral + "_coral", coralPlant));
			blocks.add(Resources.attach("dead_" + coral + "_coral", deadCoralPlant));
			blocks.add(Resources.attach(coral + "_coral_fan", fan));
			blocks.add(Resources.attach("dead_" + coral + "_coral_fan", deadFan));
		});

		Block deadPipeOrgan = new DeadCoralPlantBlock(deadCoralProps);

		blocks.add(Resources.attach("dead_pipe_organ_tentacles", deadPipeOrgan));
		blocks.add(Resources.attach("pipe_organ_tentacles", new CoralPlantBlock(deadPipeOrgan, coralProps)));

		Block.Properties bioluminescence = coralBlockProps.lightValue(15);

		blocks.add(Resources.attach("cyan_bioluminescent_coral", bioluminescence));
		blocks.add(Resources.attach("green_bioluminescent_coral", bioluminescence));
		blocks.add(Resources.attach("light_blue_bioluminescent_coral", bioluminescence));

		blocks.forEach(event.getRegistry()::register);
	}

	@SubscribeEvent
	public static void registerEntities(Register<EntityType<?>> event) {

	}

	@SubscribeEvent
	public static void registerDimensions(RegisterDimensionsEvent event) {

	}

	@SubscribeEvent
	public static void registerItems(Register<Item> event) {
		Item.Properties props = new Item.Properties().group(ItemGroups.GENERAL);

		List<Block> blocks = new ArrayList<>();
		List<Item> items = new ArrayList<>();

		blocks.add(CYAN_BIOLUMINESCENT_CORAL);
		blocks.add(GREEN_BIOLUMINESCENT_CORAL);
		blocks.add(LIGHT_BLUE_BIOLUMINESCENT_CORAL);

		items.add(Resources.attach("cyan_bioluminescent_goo", props));
		items.add(Resources.attach("light_blue_bioluminescent_goo", props));
		items.add(Resources.attach("green_bioluminescent_goo", props));

		blocks.add(BLACKGREEN_TREE_CORAL_BLOCK);
		blocks.add(BUBBLEGUM_CORAL_BLOCK);
		blocks.add(CABBAGE_TREE_CORAL_BLOCK);
		blocks.add(FLOWERTUBE_CORAL_BLOCK);
		blocks.add(GARNET_SPIRAL_CORAL_BLOCK);
		blocks.add(GLOW_FOREST_CORAL_BLOCK);
		blocks.add(GLOWTONGUE_TUBE_CORAL_BLOCK);
		blocks.add(IVORY_CORAL_BLOCK);
		blocks.add(LIME_BRAIN_CORAL_BLOCK);
		blocks.add(LOPHELIA_CORAL_BLOCK);
		blocks.add(PIPE_ORGAN_CORAL_BLOCK);
		blocks.add(RED_TREE_CORAL_BLOCK);
		blocks.add(SEAFAN_CORAL_BLOCK);
		blocks.add(STRAIGHT_WILLOW_CORAL_BLOCK);
		blocks.add(SUNRISE_CORAL_BLOCK);
		blocks.add(TABLE_CORAL_BLOCK);
		blocks.add(THIN_BLADE_CORAL_BLOCK);
		blocks.add(TRUMPETEAR_CORAL_BLOCK);

		blocks.add(DEAD_BLACKGREEN_TREE_CORAL_BLOCK);
		blocks.add(DEAD_BUBBLEGUM_CORAL_BLOCK);
		blocks.add(DEAD_CABBAGE_TREE_CORAL_BLOCK);
		blocks.add(DEAD_FLOWERTUBE_CORAL_BLOCK);
		blocks.add(DEAD_GARNET_SPIRAL_CORAL_BLOCK);
		blocks.add(DEAD_GLOW_FOREST_CORAL_BLOCK);
		blocks.add(DEAD_GLOWTONGUE_TUBE_CORAL_BLOCK);
		blocks.add(DEAD_IVORY_CORAL_BLOCK);
		blocks.add(DEAD_LIME_BRAIN_CORAL_BLOCK);
		blocks.add(DEAD_LOPHELIA_CORAL_BLOCK);
		blocks.add(DEAD_PIPE_ORGAN_CORAL_BLOCK);
		blocks.add(DEAD_RED_TREE_CORAL_BLOCK);
		blocks.add(DEAD_SEAFAN_CORAL_BLOCK);
		blocks.add(DEAD_STRAIGHT_WILLOW_CORAL_BLOCK);
		blocks.add(DEAD_SUNRISE_CORAL_BLOCK);
		blocks.add(DEAD_TABLE_CORAL_BLOCK);
		blocks.add(DEAD_THIN_BLADE_CORAL_BLOCK);
		blocks.add(DEAD_TRUMPETEAR_CORAL_BLOCK);

		blocks.add(BLACKGREEN_TREE_CORAL);
		blocks.add(BUBBLEGUM_CORAL);
		blocks.add(CABBAGE_TREE_CORAL);
		blocks.add(FLOWERTUBE_CORAL);
		blocks.add(GARNET_SPIRAL_CORAL);
		blocks.add(GLOW_FOREST_CORAL);
		blocks.add(GLOWTONGUE_TUBE_CORAL);
		blocks.add(IVORY_CORAL);
		blocks.add(LIME_BRAIN_CORAL);
		blocks.add(LOPHELIA_CORAL);
		blocks.add(PIPE_ORGAN_CORAL);
		blocks.add(PIPE_ORGAN_TENTACLES);
		blocks.add(RED_TREE_CORAL);
		blocks.add(SEAFAN_CORAL);
		blocks.add(STRAIGHT_WILLOW_CORAL);
		blocks.add(SUNRISE_CORAL);
		blocks.add(TABLE_CORAL);
		blocks.add(THIN_BLADE_CORAL);
		blocks.add(TRUMPETEAR_CORAL);

		blocks.add(DEAD_BLACKGREEN_TREE_CORAL);
		blocks.add(DEAD_BUBBLEGUM_CORAL);
		blocks.add(DEAD_CABBAGE_TREE_CORAL);
		blocks.add(DEAD_FLOWERTUBE_CORAL);
		blocks.add(DEAD_GARNET_SPIRAL_CORAL);
		blocks.add(DEAD_GLOW_FOREST_CORAL);
		blocks.add(DEAD_GLOWTONGUE_TUBE_CORAL);
		blocks.add(DEAD_IVORY_CORAL);
		blocks.add(DEAD_LIME_BRAIN_CORAL);
		blocks.add(DEAD_LOPHELIA_CORAL);
		blocks.add(DEAD_PIPE_ORGAN_CORAL);
		blocks.add(DEAD_PIPE_ORGAN_TENTACLES);
		blocks.add(DEAD_RED_TREE_CORAL);
		blocks.add(DEAD_SEAFAN_CORAL);
		blocks.add(DEAD_STRAIGHT_WILLOW_CORAL);
		blocks.add(DEAD_TABLE_CORAL);
		blocks.add(DEAD_SUNRISE_CORAL);
		blocks.add(DEAD_THIN_BLADE_CORAL);
		blocks.add(DEAD_TRUMPETEAR_CORAL);

		blocks.add(BLACKGREEN_TREE_CORAL_FAN);
		blocks.add(BUBBLEGUM_CORAL_FAN);
		blocks.add(CABBAGE_TREE_CORAL_FAN);
		blocks.add(FLOWERTUBE_CORAL_FAN);
		blocks.add(GARNET_SPIRAL_CORAL_FAN);
		blocks.add(GLOW_FOREST_CORAL_FAN);
		blocks.add(GLOWTONGUE_TUBE_CORAL_FAN);
		blocks.add(IVORY_CORAL_FAN);
		blocks.add(LIME_BRAIN_CORAL_FAN);
		blocks.add(LOPHELIA_CORAL_FAN);
		blocks.add(PIPE_ORGAN_CORAL_FAN);
		blocks.add(RED_TREE_CORAL_FAN);
		blocks.add(SEAFAN_CORAL_FAN);
		blocks.add(STRAIGHT_WILLOW_CORAL_FAN);
		blocks.add(SUNRISE_CORAL_FAN);
		blocks.add(TABLE_CORAL_FAN);
		blocks.add(THIN_BLADE_CORAL_FAN);
		blocks.add(TRUMPETEAR_CORAL_FAN);

		blocks.add(DEAD_BLACKGREEN_TREE_CORAL_FAN);
		blocks.add(DEAD_BUBBLEGUM_CORAL_FAN);
		blocks.add(DEAD_CABBAGE_TREE_CORAL_FAN);
		blocks.add(DEAD_FLOWERTUBE_CORAL_FAN);
		blocks.add(DEAD_GARNET_SPIRAL_CORAL_FAN);
		blocks.add(DEAD_GLOW_FOREST_CORAL_FAN);
		blocks.add(DEAD_GLOWTONGUE_TUBE_CORAL_FAN);
		blocks.add(DEAD_IVORY_CORAL_FAN);
		blocks.add(DEAD_LIME_BRAIN_CORAL_FAN);
		blocks.add(DEAD_LOPHELIA_CORAL_FAN);
		blocks.add(DEAD_PIPE_ORGAN_CORAL_FAN);
		blocks.add(DEAD_RED_TREE_CORAL_FAN);
		blocks.add(DEAD_SEAFAN_CORAL_FAN);
		blocks.add(DEAD_STRAIGHT_WILLOW_CORAL_FAN);
		blocks.add(DEAD_SUNRISE_CORAL_FAN);
		blocks.add(DEAD_TABLE_CORAL_FAN);
		blocks.add(DEAD_THIN_BLADE_CORAL_FAN);
		blocks.add(DEAD_TRUMPETEAR_CORAL_FAN);

		blocks.stream().map(block -> {
			Item item = new BlockItem(block, props);

			item.setRegistryName(block.getRegistryName());

			return item;
		}).forEach(event.getRegistry()::register);

		items.stream().forEach(event.getRegistry()::register);
	}
}
