package github.kingvampire.DeepTrenches.core.util.registry;

import static github.kingvampire.DeepTrenches.api.enums.CoralType.PIPE_ORGAN;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.GHOSHROOM;
import static github.kingvampire.DeepTrenches.core.init.ModEffects.BRAITOR_BEAUTY;
import static github.kingvampire.DeepTrenches.core.init.ModEffects.COSMOS_BEAUTY;
import static github.kingvampire.DeepTrenches.core.init.ModEffects.CYCAWLER_BEAUTY;
import static github.kingvampire.DeepTrenches.core.init.ModEffects.FLOWER_BEAUTY;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.block.Blocks.COCOA;
import static net.minecraft.block.Blocks.DIRT;
import static net.minecraft.block.Blocks.GRASS;
import static net.minecraft.block.Blocks.GRASS_BLOCK;
import static net.minecraft.block.Blocks.IRON_BLOCK;
import static net.minecraft.block.Blocks.OAK_LOG;
import static net.minecraft.block.Blocks.RED_TULIP;
import static net.minecraft.block.SoundType.PLANT;
import static net.minecraft.block.material.Material.ROCK;
import static net.minecraft.block.material.MaterialColor.GRAY;
import static net.minecraft.potion.Effects.FIRE_RESISTANCE;
import static net.minecraft.potion.Effects.GLOWING;
import static net.minecraft.potion.Effects.NIGHT_VISION;
import static net.minecraft.potion.Effects.POISON;
import static net.minecraft.potion.Effects.RESISTANCE;
import static net.minecraft.potion.Effects.WEAKNESS;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import github.kingvampire.DeepTrenches.api.enums.CoralType;
import github.kingvampire.DeepTrenches.api.enums.WoodType;
import github.kingvampire.DeepTrenches.core.blocks.MosoilBlock;
import github.kingvampire.DeepTrenches.core.blocks.ReebloonBlock;
import github.kingvampire.DeepTrenches.core.blocks.SproomSpikeBlock;
import github.kingvampire.DeepTrenches.core.blocks.StaspNestBlock;
import github.kingvampire.DeepTrenches.core.blocks.base.ModLogBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(bus = MOD)
public class BlockRegistry {

    @SuppressWarnings("deprecation")
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

	for (WoodType woodType : WoodType.values()) {
	    int light = woodType.getLight();
	    int general = woodType == GHOSHROOM ? light : 0;

	    Block cap = woodType.cap(light);
	    Block leaves = woodType.leaves();
	    Block planks = woodType.planks(general);
	    Block sapling = woodType.sapling(general);

	    blocks.add(planks);
	    blocks.add(sapling);
	    blocks.add(woodType.hasLeaves() ? leaves : cap);
	    blocks.add(woodType.button(general));
	    blocks.add(woodType.door(general));
	    blocks.add(woodType.fence(general));
	    blocks.add(woodType.fenceGate(general));
	    blocks.add(woodType.log(light));
	    blocks.add(woodType.pot(sapling));
	    blocks.add(woodType.pressurePlate(general));
	    blocks.add(woodType.sign(general));
	    blocks.add(woodType.slabs(general));
	    blocks.add(woodType.stairs(planks, general));
	    blocks.add(woodType.strippedLog(light));
	    blocks.add(woodType.strippedWood(light));
	    blocks.add(woodType.trapdoor(general));
	    blocks.add(woodType.wallSign(general));
	    blocks.add(woodType.wood(light));
	}

	Map<String, Block> map = Maps.newHashMap();
	Map<String, Properties> mapToBlock = Maps.newHashMap();

	Properties flower = Properties.from(RED_TULIP);
	Properties spike = Properties.from(OAK_LOG).lightValue(4).tickRandomly();

	Block sproom_spike = new SproomSpikeBlock(spike).setRegistryName(new ResourceLocation(MODID, "sproom_spike"));
	
	Block pink_rose = new FlowerBlock(FLOWER_BEAUTY, 13, flower).setRegistryName(new ResourceLocation(MODID, "pink_rose"));
	Block pink_rose_bush = new FlowerBlock(FLOWER_BEAUTY, 13, flower).setRegistryName(new ResourceLocation(MODID, "pink_rose_bush"));
	Block red_rose = new FlowerBlock(FLOWER_BEAUTY, 13, flower).setRegistryName(new ResourceLocation(MODID, "red_rose"));
	Block sweet_violet = new FlowerBlock(RESISTANCE, 9, flower).setRegistryName(new ResourceLocation(MODID, "sweet_violet"));
	Block native_violet = new FlowerBlock(RESISTANCE, 9, flower).setRegistryName(new ResourceLocation(MODID, "native_violet"));
	Block poppy = new FlowerBlock(NIGHT_VISION, 5, flower).setRegistryName(new ResourceLocation(MODID, "poppy"));
	Block blue_poppy = new FlowerBlock(NIGHT_VISION, 8, flower).setRegistryName(new ResourceLocation(MODID, "blue_poppy"));
	Block opium_poppy = new FlowerBlock(WEAKNESS, 11, flower).setRegistryName(new ResourceLocation(MODID, "opium_poppy"));
	Block gyldelion_flower = new FlowerBlock(GLOWING, 12, flower).setRegistryName(new ResourceLocation(MODID, "gyldelion_flower"));

	Block blue_violet = new FlowerBlock(RESISTANCE, 9, flower).setRegistryName(new ResourceLocation(MODID, "blue_violet"));
	Block bonnet_spurge = new FlowerBlock(POISON, 8, flower).setRegistryName(new ResourceLocation(MODID, "bonnet_spurge"));
	Block chocolate_cosmos_flower = new FlowerBlock(COSMOS_BEAUTY, 14, flower).setRegistryName(new ResourceLocation(MODID, "chocolate_cosmos_flower"));
	Block cycawler = new FlowerBlock(CYCAWLER_BEAUTY, 24000, flower).setRegistryName(new ResourceLocation(MODID, "cycawler"));
	Block garden_pinks = new FlowerBlock(FIRE_RESISTANCE, 6, flower).setRegistryName(new ResourceLocation(MODID, "garden_pinks"));
	Block red_chocolate_cosmos_flower = new FlowerBlock(COSMOS_BEAUTY, 14, flower).setRegistryName(new ResourceLocation(MODID, "red_chocolate_cosmos_flower"));
	Block spike_lavender = new FlowerBlock(FLOWER_BEAUTY, 6, flower).setRegistryName(new ResourceLocation(MODID, "spike_lavender"));
	Block topped_lavender = new FlowerBlock(FLOWER_BEAUTY, 6, flower).setRegistryName(new ResourceLocation(MODID, "topped_lavender"));
	Block pink_braitor = new FlowerBlock(BRAITOR_BEAUTY, 21, flower).setRegistryName(new ResourceLocation(MODID, "pink_braitor_flower"));
	Block red_braitor = new FlowerBlock(BRAITOR_BEAUTY, 21, flower).setRegistryName(new ResourceLocation(MODID, "red_braitor_flower"));
	Block vaslame = new FlowerBlock(FIRE_RESISTANCE, 6, flower).setRegistryName(new ResourceLocation(MODID, "vaslame"));
	Block weepy_hollower = new FlowerBlock(GLOWING, 6, flower).setRegistryName(new ResourceLocation(MODID, "weepy_hollower"));

	IForgeRegistry<Block> registry = event.getRegistry();
	
	registry.register(new TallFlowerBlock(flower).setRegistryName(new ResourceLocation(MODID, "black_lily")));
	registry.register(blue_poppy);
	registry.register(blue_violet);
	registry.register(bonnet_spurge);
	registry.register(new TallFlowerBlock(flower).setRegistryName(new ResourceLocation(MODID, "bullthorn")));
	registry.register(chocolate_cosmos_flower);
	registry.register(cycawler);
	registry.register(garden_pinks);
	registry.register(new TallFlowerBlock(flower).setRegistryName(new ResourceLocation(MODID, "giant_violet")));
	registry.register(gyldelion_flower);
	registry.register(new TallFlowerBlock(flower).setRegistryName(new ResourceLocation(MODID, "lime_spurge")));
	registry.register(new TallFlowerBlock(flower).setRegistryName(new ResourceLocation(MODID, "lupin_flower")));
	registry.register(native_violet);
	registry.register(opium_poppy);
	registry.register(new TallFlowerBlock(flower).setRegistryName(new ResourceLocation(MODID, "orange_lily")));
	registry.register(poppy);
	registry.register(pink_braitor);
	registry.register(pink_rose);
	registry.register(pink_rose_bush);
	registry.register(new TallFlowerBlock(flower).setRegistryName(new ResourceLocation(MODID, "purpround")));
	registry.register(red_braitor);
	registry.register(red_chocolate_cosmos_flower);
	registry.register(red_rose);
	registry.register(spike_lavender);
	registry.register(new TallFlowerBlock(flower).setRegistryName(new ResourceLocation(MODID, "sprinly")));
	registry.register(sproom_spike);
	registry.register(sweet_violet);
	registry.register(topped_lavender);
	registry.register(vaslame);
	registry.register(new TallFlowerBlock(flower).setRegistryName(new ResourceLocation(MODID, "velvet_lily")));
	registry.register(weepy_hollower);

	map.put("potted_blue_poppy", new FlowerPotBlock(blue_poppy, flower));
	map.put("potted_blue_violet", new FlowerPotBlock(blue_violet, flower));
	map.put("potted_bonnet_spurge", new FlowerPotBlock(bonnet_spurge, flower));
	map.put("potted_chocolate_cosmos_flower", new FlowerPotBlock(chocolate_cosmos_flower, flower));
	map.put("potted_cycawler", new FlowerPotBlock(cycawler, flower));
	map.put("potted_garden_pinks", new FlowerPotBlock(garden_pinks, flower));
	map.put("potted_gyldelion_flower", new FlowerPotBlock(gyldelion_flower, flower));
	map.put("potted_native_violet", new FlowerPotBlock(native_violet, flower));
	map.put("potted_opium_poppy", new FlowerPotBlock(opium_poppy, flower));
	map.put("potted_poppy", new FlowerPotBlock(poppy, flower));
	map.put("potted_pink_braitor_flower", new FlowerPotBlock(pink_braitor, flower));
	map.put("potted_pink_rose", new FlowerPotBlock(pink_rose, flower));
	map.put("potted_pink_rose_bush", new FlowerPotBlock(pink_rose_bush, flower));
	map.put("potted_spike_lavender", new FlowerPotBlock(spike_lavender, flower));
	map.put("potted_red_braitor_flower", new FlowerPotBlock(red_braitor, flower));
	map.put("potted_red_chocolate_cosmos_flower", new FlowerPotBlock(red_chocolate_cosmos_flower, flower));
	map.put("potted_red_rose", new FlowerPotBlock(red_rose, flower));
	map.put("potted_sproom_spike", new FlowerPotBlock(sproom_spike, flower));
	map.put("potted_sweet_violet", new FlowerPotBlock(sweet_violet, flower));
	map.put("potted_topped_lavender", new FlowerPotBlock(topped_lavender, flower));
	map.put("potted_vaslame", new FlowerPotBlock(vaslame, flower));
	map.put("potted_weepy_hollower", new FlowerPotBlock(weepy_hollower, flower));

	map.put("stasp_nest", new StaspNestBlock(Properties.from(OAK_LOG)));

	Properties biolum = Properties.create(ROCK, GRAY).hardnessAndResistance(1.5F, 6F).lightValue(15);
	Properties violet = Properties.from(COCOA).sound(PLANT);

	mapToBlock.put("cyan_bioluminescent_block", biolum);
	mapToBlock.put("green_bioluminescent_block", biolum);
	mapToBlock.put("light_blue_bioluminescent_block", biolum);
	mapToBlock.put("giant_violet_leaf", violet);
	mapToBlock.put("giant_violet_pistil", violet);
	mapToBlock.put("giant_violet_petal", violet);
	map.put("giant_violet_steam", new ModLogBlock(violet));

	mapToBlock.put("gyldelion_block", Properties.from(IRON_BLOCK));
	
	Block dritean = new Block(Properties.from(DIRT)).setRegistryName(new ResourceLocation(MODID, "dritean"));
	
	registry.register(dritean);
	registry.register(new ReebloonBlock(Properties.from(GRASS)).setRegistryName(new ResourceLocation(MODID, "reebloon")));
	registry.register(new MosoilBlock(dritean, Properties.from(GRASS_BLOCK)).setRegistryName(new ResourceLocation(MODID, "mosoil")));

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

}
