package github.kingvampire.DeepTrenches.core.util.registry;

import static github.kingvampire.DeepTrenches.core.init.ItemGroups.GENERAL;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.*;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(bus = MOD)
public class BlockItemRegistry {

    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
	List<Block> blocks = new ArrayList<>();

	blocks.add(CYAN_BIOLUMINESCENT_BLOCK);
	blocks.add(GREEN_BIOLUMINESCENT_BLOCK);
	blocks.add(LIGHT_BLUE_BIOLUMINESCENT_BLOCK);
	blocks.add(STASP_NEST);

	blocks.add(GYLDELION_BLOCK);
	blocks.add(GIANT_VIOLET_PETAL);
	blocks.add(GIANT_VIOLET_PISTIL);
	blocks.add(GIANT_VIOLET_LEAF);
	blocks.add(GIANT_VIOLET_STEAM);

	blocks.add(BLACK_LILY);
	blocks.add(BLUE_POPPY);
	blocks.add(BLUE_VIOLET);
	blocks.add(BONNET_SPURGE);
	blocks.add(BULLTHORN);
	blocks.add(CHOCOLATE_COSMOS_FLOWER);
	blocks.add(CYCAWLER);
	blocks.add(GARDEN_PINKS);
	blocks.add(GIANT_VIOLET);
	blocks.add(GYLDELION_FLOWER);
	blocks.add(LIME_SPURGE);
	blocks.add(LUPIN_FLOWER);
	blocks.add(NATIVE_VIOLET);
	blocks.add(OPIUM_POPPY);
	blocks.add(ORANGE_LILY);
	blocks.add(POPPY);
	blocks.add(PINK_BRAITOR_FLOWER);
	blocks.add(PINK_ROSE);
	blocks.add(PINK_ROSE_BUSH);
	blocks.add(PURPROUND);
	blocks.add(RED_BRAITOR_FLOWER);
	blocks.add(RED_CHOCOLATE_COSMOS_FLOWER);
	blocks.add(RED_ROSE);
	blocks.add(SPIKE_LAVENDER);
	blocks.add(SPRINLY);
	blocks.add(SWEET_VIOLET);
	blocks.add(TOPPED_LAVENDER);
	blocks.add(VASLAME);
	blocks.add(VELVET_LILY);
	blocks.add(WEEPY_HOLLOWER);
	
	blocks.add(REEBLOON);
	blocks.add(DRITEAN);
	blocks.add(MOSOIL);

	blocks.add(ALMOND_BUTTON);
	blocks.add(ANAMEATA_BUTTON);
	blocks.add(AQUEAN_BUTTON);
	blocks.add(BARSHROOKLE_BUTTON);
	blocks.add(BLACK_BIRCH_BUTTON);
	blocks.add(CHERRY_BUTTON);
	blocks.add(COOK_PINE_BUTTON);
	blocks.add(CROLOOD_BUTTON);
	blocks.add(DARK_CROLOOD_BUTTON);
	blocks.add(EBONY_BUTTON);
	blocks.add(FUNERANITE_BUTTON);
	blocks.add(FUCHSITRA_BUTTON);
	blocks.add(GHOSHROOM_BUTTON);
	blocks.add(PELTOGYNE_BUTTON);
	blocks.add(PIN_CHERRY_BUTTON);
	blocks.add(PLUM_BUTTON);
	blocks.add(PURFUNGA_BUTTON);
	blocks.add(SPROOM_BUTTON);
	blocks.add(STORTREEAN_BUTTON);
	blocks.add(STROOMEAN_BUTTON);
	blocks.add(SUNRISE_FUNGUS_BUTTON);
	blocks.add(TEAK_BUTTON);
	blocks.add(THUNDERCLOUD_PLUM_BUTTON);

	blocks.add(ALMOND_DOOR);
	blocks.add(ANAMEATA_DOOR);
	blocks.add(AQUEAN_DOOR);
	blocks.add(BARSHROOKLE_DOOR);
	blocks.add(BLACK_BIRCH_DOOR);
	blocks.add(CHERRY_DOOR);
	blocks.add(COOK_PINE_DOOR);
	blocks.add(CROLOOD_DOOR);
	blocks.add(DARK_CROLOOD_DOOR);
	blocks.add(EBONY_DOOR);
	blocks.add(FUNERANITE_DOOR);
	blocks.add(FUCHSITRA_DOOR);
	blocks.add(GHOSHROOM_DOOR);
	blocks.add(PELTOGYNE_DOOR);
	blocks.add(PIN_CHERRY_DOOR);
	blocks.add(PLUM_DOOR);
	blocks.add(PURFUNGA_DOOR);
	blocks.add(SPROOM_DOOR);
	blocks.add(STORTREEAN_DOOR);
	blocks.add(STROOMEAN_DOOR);
	blocks.add(SUNRISE_FUNGUS_DOOR);
	blocks.add(TEAK_DOOR);
	blocks.add(THUNDERCLOUD_PLUM_DOOR);

	blocks.add(ALMOND_FENCE);
	blocks.add(ANAMEATA_FENCE);
	blocks.add(AQUEAN_FENCE);
	blocks.add(BARSHROOKLE_FENCE);
	blocks.add(BLACK_BIRCH_FENCE);
	blocks.add(CHERRY_FENCE);
	blocks.add(COOK_PINE_FENCE);
	blocks.add(CROLOOD_FENCE);
	blocks.add(DARK_CROLOOD_FENCE);
	blocks.add(EBONY_FENCE);
	blocks.add(FUNERANITE_FENCE);
	blocks.add(FUCHSITRA_FENCE);
	blocks.add(GHOSHROOM_FENCE);
	blocks.add(PELTOGYNE_FENCE);
	blocks.add(PIN_CHERRY_FENCE);
	blocks.add(PLUM_FENCE);
	blocks.add(PURFUNGA_FENCE);
	blocks.add(SPROOM_FENCE);
	blocks.add(STORTREEAN_FENCE);
	blocks.add(STROOMEAN_FENCE);
	blocks.add(SUNRISE_FUNGUS_FENCE);
	blocks.add(TEAK_FENCE);
	blocks.add(THUNDERCLOUD_PLUM_FENCE);

	blocks.add(ALMOND_FENCE_GATE);
	blocks.add(ANAMEATA_FENCE_GATE);
	blocks.add(AQUEAN_FENCE_GATE);
	blocks.add(BARSHROOKLE_FENCE_GATE);
	blocks.add(BLACK_BIRCH_FENCE_GATE);
	blocks.add(CHERRY_FENCE_GATE);
	blocks.add(COOK_PINE_FENCE_GATE);
	blocks.add(CROLOOD_FENCE_GATE);
	blocks.add(DARK_CROLOOD_FENCE_GATE);
	blocks.add(EBONY_FENCE_GATE);
	blocks.add(FUNERANITE_FENCE_GATE);
	blocks.add(FUCHSITRA_FENCE_GATE);
	blocks.add(GHOSHROOM_FENCE_GATE);
	blocks.add(PELTOGYNE_FENCE_GATE);
	blocks.add(PIN_CHERRY_FENCE_GATE);
	blocks.add(PLUM_FENCE_GATE);
	blocks.add(PURFUNGA_FENCE_GATE);
	blocks.add(SPROOM_FENCE_GATE);
	blocks.add(STORTREEAN_FENCE_GATE);
	blocks.add(STROOMEAN_FENCE_GATE);
	blocks.add(SUNRISE_FUNGUS_FENCE_GATE);
	blocks.add(TEAK_FENCE_GATE);
	blocks.add(THUNDERCLOUD_PLUM_FENCE_GATE);

	blocks.add(ALMOND_LEAVES);
	blocks.add(AQUEAN_LEAVES);
	blocks.add(BLACK_BIRCH_LEAVES);
	blocks.add(CHERRY_LEAVES);
	blocks.add(COOK_PINE_LEAVES);
	blocks.add(CROLOOD_LEAVES);
	blocks.add(DARK_CROLOOD_LEAVES);
	blocks.add(EBONY_LEAVES);
	blocks.add(FUCHSITRA_LEAVES);
	blocks.add(PELTOGYNE_LEAVES);
	blocks.add(PIN_CHERRY_LEAVES);
	blocks.add(PLUM_LEAVES);
	blocks.add(SPROOM_LEAVES);
	blocks.add(STORTREEAN_LEAVES);
	blocks.add(TEAK_LEAVES);
	blocks.add(THUNDERCLOUD_PLUM_LEAVES);

	blocks.add(ANAMEATA_CAP);
	blocks.add(BARSHROOKLE_CAP);
	blocks.add(FUNERANITE_CAP);
	blocks.add(GHOSHROOM_CAP);
	blocks.add(PURFUNGA_CAP);
	blocks.add(STROOMEAN_CAP);
	blocks.add(SUNRISE_FUNGUS_CAP);

	blocks.add(ALMOND_LOG);
	blocks.add(ANAMEATA_LOG);
	blocks.add(AQUEAN_LOG);
	blocks.add(BARSHROOKLE_LOG);
	blocks.add(BLACK_BIRCH_LOG);
	blocks.add(CHERRY_LOG);
	blocks.add(COOK_PINE_LOG);
	blocks.add(CROLOOD_LOG);
	blocks.add(DARK_CROLOOD_LOG);
	blocks.add(EBONY_LOG);
	blocks.add(FUNERANITE_LOG);
	blocks.add(FUCHSITRA_LOG);
	blocks.add(GHOSHROOM_LOG);
	blocks.add(PELTOGYNE_LOG);
	blocks.add(PIN_CHERRY_LOG);
	blocks.add(PLUM_LOG);
	blocks.add(PURFUNGA_LOG);
	blocks.add(SPROOM_LOG);
	blocks.add(STORTREEAN_LOG);
	blocks.add(STROOMEAN_LOG);
	blocks.add(SUNRISE_FUNGUS_LOG);
	blocks.add(TEAK_LOG);
	blocks.add(THUNDERCLOUD_PLUM_LOG);

	blocks.add(SPROOM_SPIKE);

	blocks.add(STRIPPED_ALMOND_LOG);
	blocks.add(STRIPPED_ANAMEATA_LOG);
	blocks.add(STRIPPED_AQUEAN_LOG);
	blocks.add(STRIPPED_BARSHROOKLE_LOG);
	blocks.add(STRIPPED_BLACK_BIRCH_LOG);
	blocks.add(STRIPPED_CHERRY_LOG);
	blocks.add(STRIPPED_COOK_PINE_LOG);
	blocks.add(STRIPPED_CROLOOD_LOG);
	blocks.add(STRIPPED_DARK_CROLOOD_LOG);
	blocks.add(STRIPPED_EBONY_LOG);
	blocks.add(STRIPPED_FUNERANITE_LOG);
	blocks.add(STRIPPED_FUCHSITRA_LOG);
	blocks.add(STRIPPED_GHOSHROOM_LOG);
	blocks.add(STRIPPED_PELTOGYNE_LOG);
	blocks.add(STRIPPED_PIN_CHERRY_LOG);
	blocks.add(STRIPPED_PLUM_LOG);
	blocks.add(STRIPPED_PURFUNGA_LOG);
	blocks.add(STRIPPED_SPROOM_LOG);
	blocks.add(STRIPPED_STORTREEAN_LOG);
	blocks.add(STRIPPED_STROOMEAN_LOG);
	blocks.add(STRIPPED_SUNRISE_FUNGUS_LOG);
	blocks.add(STRIPPED_TEAK_LOG);
	blocks.add(STRIPPED_THUNDERCLOUD_PLUM_LOG);

	blocks.add(ALMOND_PLANKS);
	blocks.add(ANAMEATA_PLANKS);
	blocks.add(AQUEAN_PLANKS);
	blocks.add(BARSHROOKLE_PLANKS);
	blocks.add(BLACK_BIRCH_PLANKS);
	blocks.add(CHERRY_PLANKS);
	blocks.add(COOK_PINE_PLANKS);
	blocks.add(CROLOOD_PLANKS);
	blocks.add(DARK_CROLOOD_PLANKS);
	blocks.add(EBONY_PLANKS);
	blocks.add(FUNERANITE_PLANKS);
	blocks.add(FUCHSITRA_PLANKS);
	blocks.add(GHOSHROOM_PLANKS);
	blocks.add(PELTOGYNE_PLANKS);
	blocks.add(PIN_CHERRY_PLANKS);
	blocks.add(PLUM_PLANKS);
	blocks.add(PURFUNGA_PLANKS);
	blocks.add(SPROOM_PLANKS);
	blocks.add(STORTREEAN_PLANKS);
	blocks.add(STROOMEAN_PLANKS);
	blocks.add(SUNRISE_FUNGUS_PLANKS);
	blocks.add(TEAK_PLANKS);
	blocks.add(THUNDERCLOUD_PLUM_PLANKS);

	blocks.add(ALMOND_PRESSURE_PLATE);
	blocks.add(ANAMEATA_PRESSURE_PLATE);
	blocks.add(AQUEAN_PRESSURE_PLATE);
	blocks.add(BARSHROOKLE_PRESSURE_PLATE);
	blocks.add(BLACK_BIRCH_PRESSURE_PLATE);
	blocks.add(CHERRY_PRESSURE_PLATE);
	blocks.add(COOK_PINE_PRESSURE_PLATE);
	blocks.add(CROLOOD_PRESSURE_PLATE);
	blocks.add(DARK_CROLOOD_PRESSURE_PLATE);
	blocks.add(EBONY_PRESSURE_PLATE);
	blocks.add(FUNERANITE_PRESSURE_PLATE);
	blocks.add(FUCHSITRA_PRESSURE_PLATE);
	blocks.add(GHOSHROOM_PRESSURE_PLATE);
	blocks.add(PELTOGYNE_PRESSURE_PLATE);
	blocks.add(PIN_CHERRY_PRESSURE_PLATE);
	blocks.add(PLUM_PRESSURE_PLATE);
	blocks.add(PURFUNGA_PRESSURE_PLATE);
	blocks.add(SPROOM_PRESSURE_PLATE);
	blocks.add(STORTREEAN_PRESSURE_PLATE);
	blocks.add(STROOMEAN_PRESSURE_PLATE);
	blocks.add(SUNRISE_FUNGUS_PRESSURE_PLATE);
	blocks.add(TEAK_PRESSURE_PLATE);
	blocks.add(THUNDERCLOUD_PLUM_PRESSURE_PLATE);

	blocks.add(ALMOND_SAPLING);
	blocks.add(ANAMEATA_SAPLING);
	blocks.add(AQUEAN_SAPLING);
	blocks.add(BARSHROOKLE_SAPLING);
	blocks.add(BLACK_BIRCH_SAPLING);
	blocks.add(CHERRY_SAPLING);
	blocks.add(COOK_PINE_SAPLING);
	blocks.add(CROLOOD_SAPLING);
	blocks.add(DARK_CROLOOD_SAPLING);
	blocks.add(EBONY_SAPLING);
	blocks.add(FUNERANITE_SAPLING);
	blocks.add(FUCHSITRA_SAPLING);
	blocks.add(GHOSHROOM_SAPLING);
	blocks.add(PELTOGYNE_SAPLING);
	blocks.add(PIN_CHERRY_SAPLING);
	blocks.add(PLUM_SAPLING);
	blocks.add(PURFUNGA_SAPLING);
	blocks.add(SPROOM_SAPLING);
	blocks.add(STORTREEAN_SAPLING);
	blocks.add(STROOMEAN_SAPLING);
	blocks.add(SUNRISE_FUNGUS_SAPLING);
	blocks.add(TEAK_SAPLING);
	blocks.add(THUNDERCLOUD_PLUM_SAPLING);

	blocks.add(ALMOND_SLAB);
	blocks.add(ANAMEATA_SLAB);
	blocks.add(AQUEAN_SLAB);
	blocks.add(BARSHROOKLE_SLAB);
	blocks.add(BLACK_BIRCH_SLAB);
	blocks.add(CHERRY_SLAB);
	blocks.add(COOK_PINE_SLAB);
	blocks.add(CROLOOD_SLAB);
	blocks.add(DARK_CROLOOD_SLAB);
	blocks.add(EBONY_SLAB);
	blocks.add(FUNERANITE_SLAB);
	blocks.add(FUCHSITRA_SLAB);
	blocks.add(GHOSHROOM_SLAB);
	blocks.add(PELTOGYNE_SLAB);
	blocks.add(PIN_CHERRY_SLAB);
	blocks.add(PLUM_SLAB);
	blocks.add(PURFUNGA_SLAB);
	blocks.add(SPROOM_SLAB);
	blocks.add(STORTREEAN_SLAB);
	blocks.add(STROOMEAN_SLAB);
	blocks.add(SUNRISE_FUNGUS_SLAB);
	blocks.add(TEAK_SLAB);
	blocks.add(THUNDERCLOUD_PLUM_SLAB);

	blocks.add(ALMOND_STAIRS);
	blocks.add(ANAMEATA_STAIRS);
	blocks.add(AQUEAN_STAIRS);
	blocks.add(BARSHROOKLE_STAIRS);
	blocks.add(BLACK_BIRCH_STAIRS);
	blocks.add(CHERRY_STAIRS);
	blocks.add(COOK_PINE_STAIRS);
	blocks.add(CROLOOD_STAIRS);
	blocks.add(DARK_CROLOOD_STAIRS);
	blocks.add(EBONY_STAIRS);
	blocks.add(FUNERANITE_STAIRS);
	blocks.add(FUCHSITRA_STAIRS);
	blocks.add(GHOSHROOM_STAIRS);
	blocks.add(PELTOGYNE_STAIRS);
	blocks.add(PIN_CHERRY_STAIRS);
	blocks.add(PLUM_STAIRS);
	blocks.add(PURFUNGA_STAIRS);
	blocks.add(SPROOM_STAIRS);
	blocks.add(STORTREEAN_STAIRS);
	blocks.add(STROOMEAN_STAIRS);
	blocks.add(SUNRISE_FUNGUS_STAIRS);
	blocks.add(TEAK_STAIRS);
	blocks.add(THUNDERCLOUD_PLUM_STAIRS);

	blocks.add(ALMOND_TRAPDOOR);
	blocks.add(ANAMEATA_TRAPDOOR);
	blocks.add(AQUEAN_TRAPDOOR);
	blocks.add(BARSHROOKLE_TRAPDOOR);
	blocks.add(BLACK_BIRCH_TRAPDOOR);
	blocks.add(CHERRY_TRAPDOOR);
	blocks.add(COOK_PINE_TRAPDOOR);
	blocks.add(CROLOOD_TRAPDOOR);
	blocks.add(DARK_CROLOOD_TRAPDOOR);
	blocks.add(EBONY_TRAPDOOR);
	blocks.add(FUNERANITE_TRAPDOOR);
	blocks.add(FUCHSITRA_TRAPDOOR);
	blocks.add(GHOSHROOM_TRAPDOOR);
	blocks.add(PELTOGYNE_TRAPDOOR);
	blocks.add(PIN_CHERRY_TRAPDOOR);
	blocks.add(PLUM_TRAPDOOR);
	blocks.add(PURFUNGA_TRAPDOOR);
	blocks.add(SPROOM_TRAPDOOR);
	blocks.add(STORTREEAN_TRAPDOOR);
	blocks.add(STROOMEAN_TRAPDOOR);
	blocks.add(SUNRISE_FUNGUS_TRAPDOOR);
	blocks.add(TEAK_TRAPDOOR);
	blocks.add(THUNDERCLOUD_PLUM_TRAPDOOR);

	blocks.add(ALMOND_WOOD);
	blocks.add(ANAMEATA_WOOD);
	blocks.add(AQUEAN_WOOD);
	blocks.add(BARSHROOKLE_WOOD);
	blocks.add(BLACK_BIRCH_WOOD);
	blocks.add(CHERRY_WOOD);
	blocks.add(COOK_PINE_WOOD);
	blocks.add(CROLOOD_WOOD);
	blocks.add(DARK_CROLOOD_WOOD);
	blocks.add(EBONY_WOOD);
	blocks.add(FUNERANITE_WOOD);
	blocks.add(FUCHSITRA_WOOD);
	blocks.add(GHOSHROOM_WOOD);
	blocks.add(PELTOGYNE_WOOD);
	blocks.add(PIN_CHERRY_WOOD);
	blocks.add(PLUM_WOOD);
	blocks.add(PURFUNGA_WOOD);
	blocks.add(SPROOM_WOOD);
	blocks.add(STORTREEAN_WOOD);
	blocks.add(STROOMEAN_WOOD);
	blocks.add(SUNRISE_FUNGUS_WOOD);
	blocks.add(TEAK_WOOD);
	blocks.add(THUNDERCLOUD_PLUM_WOOD);

	blocks.add(STRIPPED_ALMOND_WOOD);
	blocks.add(STRIPPED_ANAMEATA_WOOD);
	blocks.add(STRIPPED_AQUEAN_WOOD);
	blocks.add(STRIPPED_BARSHROOKLE_WOOD);
	blocks.add(STRIPPED_BLACK_BIRCH_WOOD);
	blocks.add(STRIPPED_CHERRY_WOOD);
	blocks.add(STRIPPED_COOK_PINE_WOOD);
	blocks.add(STRIPPED_CROLOOD_WOOD);
	blocks.add(STRIPPED_DARK_CROLOOD_WOOD);
	blocks.add(STRIPPED_EBONY_WOOD);
	blocks.add(STRIPPED_FUNERANITE_WOOD);
	blocks.add(STRIPPED_FUCHSITRA_WOOD);
	blocks.add(STRIPPED_GHOSHROOM_WOOD);
	blocks.add(STRIPPED_PELTOGYNE_WOOD);
	blocks.add(STRIPPED_PIN_CHERRY_WOOD);
	blocks.add(STRIPPED_PLUM_WOOD);
	blocks.add(STRIPPED_PURFUNGA_WOOD);
	blocks.add(STRIPPED_SPROOM_WOOD);
	blocks.add(STRIPPED_STORTREEAN_WOOD);
	blocks.add(STRIPPED_STROOMEAN_WOOD);
	blocks.add(STRIPPED_SUNRISE_FUNGUS_WOOD);
	blocks.add(STRIPPED_TEAK_WOOD);
	blocks.add(STRIPPED_THUNDERCLOUD_PLUM_WOOD);

	blocks.add(BLACKGREEN_TREE_CORAL_BLOCK);
	blocks.add(BROCCOLI_CORAL_BLOCK);
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
	blocks.add(DEAD_BROCCOLI_CORAL_BLOCK);
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
	blocks.add(BROCCOLI_CORAL);
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
	blocks.add(DEAD_BROCCOLI_CORAL);
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
	blocks.add(BROCCOLI_CORAL_FAN);
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
	blocks.add(DEAD_BROCCOLI_CORAL_FAN);
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
	    ResourceLocation id = block.getRegistryName();
	    Properties props = new Properties().group(GENERAL);

	    Item item = new BlockItem(block, props);

	    register.register(item.setRegistryName(id));
	}

    }

}
