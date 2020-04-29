package github.kingvampire.DeepTrenches.core.util;

import static github.kingvampire.DeepTrenches.api.enums.CoralType.PIPE_ORGAN;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.GHOSHROOM;
import static github.kingvampire.DeepTrenches.core.init.ItemGroups.GENERAL;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.*;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.block.Blocks.COCOA;
import static net.minecraft.block.material.MaterialColor.GRAY;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import github.kingvampire.DeepTrenches.api.enums.CoralType;
import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(bus = MOD)
public class RegistryHandler {

	@SubscribeEvent
	public static void registerBlocks(Register<Block> event) {
		List<Block> blocks = new ArrayList<>();

		for (CoralType coralType : CoralType.values()) {
			Block deadBlock = coralType.deadBlock();
			Block deadCoral = coralType.deadCoral();
			Block deadFan = coralType.deadFan();

			blocks.add(deadBlock);
			blocks.add(coralType.coralBlock(deadBlock));
			blocks.add(deadCoral);
			blocks.add(coralType.coral(deadCoral));
			blocks.add(deadFan);
			blocks.add(coralType.fan(deadFan));

			if (coralType == PIPE_ORGAN) {
				Block deadTentacles = coralType.deadTentacles();

				blocks.add(deadTentacles);
				blocks.add(coralType.tentacles(deadTentacles));
			}
		}

		Properties biolum = Properties.create(ROCK, GRAY).hardnessAndResistance(1.5F, 6F).lightValue(15);

		mapToBlock.put("cyan_bioluminescent_coral", biolum);
		mapToBlock.put("green_bioluminescent_coral", biolum);
		mapToBlock.put("light_blue_bioluminescent_coral", biolum);

		IForgeRegistry<Block> registry = event.getRegistry();

		for (Block block : blocks) {
			registry.register(block);
		}

		for (Entry<String, Block> entry : map.entrySet()) {
			ResourceLocation id = new ResourceLocation(MODID, entry.getKey());
			Block block = entry.getValue();

			registry.register(block.setRegistryName(id));
		}

		for (Entry<String, Properties> entry : mapToBlock.entrySet()) {
			ResourceLocation id = new ResourceLocation(MODID, entry.getKey());
			Block block = new Block(entry.getValue());

			registry.register(block.setRegistryName(id));
		}

	}

	@SubscribeEvent
	public static void registerDimensions(RegisterDimensionsEvent event) {

	}

	@SubscribeEvent
	public static void registerEntities(Register<EntityType<?>> event) {

	}

	@SubscribeEvent
	public static void registerDimensions(RegisterDimensionsEvent event) {

	}

	@SubscribeEvent
	public static void registerItems(Register<Item> event) {
		Map<String, Item.Properties> map = Maps.newHashMap();

		List<Block> blocks = new ArrayList<>();

		blocks.add(CYAN_BIOLUMINESCENT_CORAL);
		blocks.add(GREEN_BIOLUMINESCENT_CORAL);
		blocks.add(LIGHT_BLUE_BIOLUMINESCENT_CORAL);

		map.put("cyan_bioluminescent_goo", new Item.Properties().group(GENERAL));
		map.put("light_blue_bioluminescent_goo", new Item.Properties().group(GENERAL));
		map.put("green_bioluminescent_goo", new Item.Properties().group(GENERAL));

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

		IForgeRegistry<Item> register = event.getRegistry();

		for (Block block : blocks) {
			Item.Properties props = new Item.Properties().group(GENERAL);

			ResourceLocation id = block.getRegistryName();
			Item item = new BlockItem(block, props);

			register.register(item.setRegistryName(id));
		}

		for (Entry<String, Item.Properties> entry : map.entrySet()) {
			ResourceLocation id = new ResourceLocation(MODID, entry.getKey());
			Item item = new Item(entry.getValue());

			register.register(item.setRegistryName(id));
		}

	}
}
