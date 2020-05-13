package github.kingvampire.DeepTrenches.core.util;

import static github.kingvampire.DeepTrenches.api.enums.CoralType.PIPE_ORGAN;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.GHOSHROOM;
import static github.kingvampire.DeepTrenches.core.init.ItemGroups.GENERAL;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.*;
import static github.kingvampire.DeepTrenches.core.init.ModEffects.FLOWER_BEAUTY;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.DEEP_LAKE_BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.STASP;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.block.Blocks.COCOA;
import static net.minecraft.block.Blocks.IRON_BLOCK;
import static net.minecraft.block.Blocks.OAK_LOG;
import static net.minecraft.block.Blocks.RED_TULIP;
import static net.minecraft.block.SoundType.PLANT;
import static net.minecraft.block.material.Material.ROCK;
import static net.minecraft.block.material.MaterialColor.GRAY;
import static net.minecraft.entity.EntityClassification.MISC;
import static net.minecraft.item.Foods.APPLE;
import static net.minecraft.item.Foods.SWEET_BERRIES;
import static net.minecraft.potion.Effects.GLOWING;
import static net.minecraft.potion.Effects.NIGHT_VISION;
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
import github.kingvampire.DeepTrenches.core.blocks.SproomSpikeBlock;
import github.kingvampire.DeepTrenches.core.blocks.StaspNestBlock;
import github.kingvampire.DeepTrenches.core.enchantments.DrainingEnchantment;
import github.kingvampire.DeepTrenches.core.entity.AdaiggerEntity;
import github.kingvampire.DeepTrenches.core.entity.BoatEntityDT;
import github.kingvampire.DeepTrenches.core.entity.SignTileEntityDT;
import github.kingvampire.DeepTrenches.core.entity.StaspNestTileEntity;
import github.kingvampire.DeepTrenches.core.items.AdaiggerItem;
import github.kingvampire.DeepTrenches.core.items.BettaBucketItem;
import github.kingvampire.DeepTrenches.core.items.BoatItemDT;
import github.kingvampire.DeepTrenches.core.items.DeepLakeBettaBucketItem;
import github.kingvampire.DeepTrenches.core.potion.DrainingEffect;
import github.kingvampire.DeepTrenches.core.potion.SleepyEffect;
import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(bus = MOD)
public class RegistryHandler {

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

	Block sproom_spike = new SproomSpikeBlock(spike);
	Block pink_rose = new FlowerBlock(FLOWER_BEAUTY, 13, flower);
	Block pink_rose_bush = new FlowerBlock(FLOWER_BEAUTY, 13, flower);
	Block red_rose = new FlowerBlock(FLOWER_BEAUTY, 13, flower);
	Block sweet_violet = new FlowerBlock(RESISTANCE, 9, flower);
	Block native_violet = new FlowerBlock(RESISTANCE, 9, flower);
	Block poppy = new FlowerBlock(NIGHT_VISION, 5, flower);
	Block blue_poppy = new FlowerBlock(NIGHT_VISION, 8, flower);
	Block opium_poppy = new FlowerBlock(WEAKNESS, 11, flower);
	Block gyldelion_flower = new FlowerBlock(GLOWING, 12, flower);

	map.put("pink_rose", pink_rose);
	map.put("pink_rose_bush", pink_rose_bush);
	map.put("red_rose", red_rose);
	map.put("sweet_violet", sweet_violet);
	map.put("native_violet", native_violet);
	map.put("poppy", poppy);
	map.put("blue_poppy", blue_poppy);
	map.put("opium_poppy", opium_poppy);
	map.put("gyldelion_flower", gyldelion_flower);
	map.put("sproom_spike", sproom_spike);

	map.put("potted_pink_rose", new FlowerPotBlock(pink_rose, flower));
	map.put("potted_pink_rose_bush", new FlowerPotBlock(pink_rose_bush, flower));
	map.put("potted_red_rose", new FlowerPotBlock(red_rose, flower));
	map.put("potted_sweet_violet", new FlowerPotBlock(sweet_violet, flower));
	map.put("potted_native_violet", new FlowerPotBlock(native_violet, flower));
	map.put("potted_poppy", new FlowerPotBlock(poppy, flower));
	map.put("potted_blue_poppy", new FlowerPotBlock(blue_poppy, flower));
	map.put("potted_opium_poppy", new FlowerPotBlock(opium_poppy, flower));
	map.put("potted_gyldelion_flower", new FlowerPotBlock(gyldelion_flower, flower));
	map.put("potted_sproom_spike", new FlowerPotBlock(sproom_spike, flower));
	map.put("stasp_nest", new StaspNestBlock(Properties.from(OAK_LOG)));

	Properties biolum = Properties.create(ROCK, GRAY).hardnessAndResistance(1.5F, 6F).lightValue(15);
	Properties violet = Properties.from(COCOA).sound(PLANT);

	mapToBlock.put("cyan_bioluminescent_coral", biolum);
	mapToBlock.put("green_bioluminescent_coral", biolum);
	mapToBlock.put("light_blue_bioluminescent_coral", biolum);
	mapToBlock.put("giant_violet_pistil", violet);
	mapToBlock.put("giant_violet_petal", violet);
	mapToBlock.put("gyldelion_block", Properties.from(IRON_BLOCK));

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
	List<EntityType<?>> types = new ArrayList<>();

	EntityType<AdaiggerEntity> adaigger = EntityType.Builder
		.<AdaiggerEntity>create(AdaiggerEntity::new, MISC)
		.size(0.5F, 0.5F)
		.setCustomClientFactory(AdaiggerEntity::new)
		.build(MODID + ".adaigger");

	EntityType<BoatEntityDT> boat = EntityType.Builder
		.<BoatEntityDT>create(BoatEntityDT::new, MISC)
		.size(1.375F, 0.5625F)
		.setCustomClientFactory(BoatEntityDT::new)
		.build(MODID + ".boat");

	types.add(adaigger.setRegistryName(new ResourceLocation(MODID, "adaigger")));
	types.add(boat.setRegistryName(new ResourceLocation(MODID, "boat")));

	types.forEach(event.getRegistry()::register);
    }

    @SubscribeEvent
    public static void registerEnchantments(Register<Enchantment> event) {
	Map<String, Enchantment> enchantments = Maps.newHashMap();

	enchantments.put("draining", new DrainingEnchantment());

	for (Entry<String, Enchantment> entry : enchantments.entrySet()) {
	    Enchantment effect = entry.getValue();
	    String path = entry.getKey();

	    effect.setRegistryName(new ResourceLocation(MODID, path));

	    event.getRegistry().register(effect);
	}
    }

    @SubscribeEvent
    public static void registerEffects(Register<Effect> event) {
	Map<String, Effect> effects = Maps.newHashMap();

	effects.put("draining", new DrainingEffect());
	effects.put("sleepy", new SleepyEffect());

	for (Entry<String, Effect> entry : effects.entrySet()) {
	    Effect effect = entry.getValue();
	    String path = entry.getKey();

	    effect.setRegistryName(new ResourceLocation(MODID, path));

	    event.getRegistry().register(effect);
	}
    }

    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
	Map<String, Item.Properties> map = Maps.newHashMap();
	Map<String, Item> items = Maps.newHashMap();

	List<Block> blocks = new ArrayList<>();

	map.put("cyan_bioluminescent_goo", new Item.Properties().group(GENERAL));
	map.put("light_blue_bioluminescent_goo", new Item.Properties().group(GENERAL));
	map.put("green_bioluminescent_goo", new Item.Properties().group(GENERAL));

	map.put("almond", new Item.Properties().group(GENERAL).food(SWEET_BERRIES));
	map.put("almond_drupe", new Item.Properties().group(GENERAL));
	map.put("cherry", new Item.Properties().group(GENERAL).food(SWEET_BERRIES));
	map.put("crolood_fruit", new Item.Properties().group(GENERAL).food(APPLE));
	map.put("dark_crolood_fruit", new Item.Properties().group(GENERAL).food(APPLE));
	map.put("persimmon_fruit", new Item.Properties().group(GENERAL).food(APPLE));
	map.put("pin_cherry", new Item.Properties().group(GENERAL).food(SWEET_BERRIES));
	map.put("plum", new Item.Properties().group(GENERAL).food(APPLE));
	map.put("thundercloud_plum", new Item.Properties().group(GENERAL).food(APPLE));

	map.put("aquean_sap", new Item.Properties().group(GENERAL));

	map.put("gyldelion_dye", new Item.Properties().group(GENERAL));
	map.put("gyldelion_ingot", new Item.Properties().group(GENERAL));
	map.put("gyldelion_nugget", new Item.Properties().group(GENERAL));
	map.put("gyldelion_stick", new Item.Properties().group(GENERAL));
	map.put("bottle_of_aquean_sap", new Item.Properties().group(GENERAL));
	
	map.put("betta", new Item.Properties().group(GENERAL));
	map.put("deep_lake_betta", new Item.Properties().group(GENERAL));

	items.put("adaigger", new AdaiggerItem());
	items.put("betta_bucket", new BettaBucketItem());
	items.put("deep_lake_betta_bucket", new DeepLakeBettaBucketItem());

	Item.Properties properties = new Item.Properties().maxStackSize(1).group(GENERAL);
	
	items.put("betta_spawn_egg", new SpawnEggItem(BETTA, 7347502, 9183521, properties));
	items.put("deep_lake_betta_spawn_egg", new SpawnEggItem(DEEP_LAKE_BETTA, 1189390, 5013319, properties));
	items.put("stasp_spawn_egg", new SpawnEggItem(STASP, 2695792, 5124510, properties));
	
	for (WoodType woodType : WoodType.values())
	    map.put(woodType + "_stick", new Item.Properties().group(GENERAL));

	blocks.add(CYAN_BIOLUMINESCENT_CORAL);
	blocks.add(GREEN_BIOLUMINESCENT_CORAL);
	blocks.add(LIGHT_BLUE_BIOLUMINESCENT_CORAL);
	blocks.add(STASP_NEST);

	blocks.add(PINK_ROSE);
	blocks.add(PINK_ROSE_BUSH);
	blocks.add(RED_ROSE);
	blocks.add(SWEET_VIOLET);
	blocks.add(NATIVE_VIOLET);
	blocks.add(GIANT_VIOLET_PISTIL);
	blocks.add(GIANT_VIOLET_PETAL);
	blocks.add(POPPY);
	blocks.add(BLUE_POPPY);
	blocks.add(OPIUM_POPPY);
	blocks.add(GYLDELION_FLOWER);
	blocks.add(GYLDELION_BLOCK);

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

	Item.Properties sign = new Item.Properties().group(GENERAL).maxStackSize(16);

	items.put("almond_sign", new SignItem(sign, ALMOND_SIGN, ALMOND_WALL_SIGN));
	items.put("anameata_sign", new SignItem(sign, ANAMEATA_SIGN, ANAMEATA_WALL_SIGN));
	items.put("aquean_sign", new SignItem(sign, AQUEAN_SIGN, AQUEAN_WALL_SIGN));
	items.put("barshrookle_sign", new SignItem(sign, BARSHROOKLE_SIGN, BARSHROOKLE_WALL_SIGN));
	items.put("black_birch_sign", new SignItem(sign, BLACK_BIRCH_SIGN, BLACK_BIRCH_WALL_SIGN));
	items.put("cherry_sign", new SignItem(sign, CHERRY_SIGN, CHERRY_WALL_SIGN));
	items.put("cook_pine_sign", new SignItem(sign, COOK_PINE_SIGN, COOK_PINE_WALL_SIGN));
	items.put("crolood_sign", new SignItem(sign, CROLOOD_SIGN, CROLOOD_WALL_SIGN));
	items.put("dark_crolood_sign", new SignItem(sign, DARK_CROLOOD_SIGN, DARK_CROLOOD_WALL_SIGN));
	items.put("ebony_sign", new SignItem(sign, EBONY_SIGN, EBONY_WALL_SIGN));
	items.put("funeranite_sign", new SignItem(sign, FUNERANITE_SIGN, FUNERANITE_WALL_SIGN));
	items.put("fuchsitra_sign", new SignItem(sign, FUCHSITRA_SIGN, FUCHSITRA_WALL_SIGN));
	items.put("ghoshroom_sign", new SignItem(sign, GHOSHROOM_SIGN, GHOSHROOM_WALL_SIGN));
	items.put("peltogyne_sign", new SignItem(sign, PELTOGYNE_SIGN, PELTOGYNE_WALL_SIGN));
	items.put("pin_cherry_sign", new SignItem(sign, PIN_CHERRY_SIGN, PIN_CHERRY_WALL_SIGN));
	items.put("plum_sign", new SignItem(sign, PLUM_SIGN, PLUM_WALL_SIGN));
	items.put("purfunga_sign", new SignItem(sign, PURFUNGA_SIGN, PURFUNGA_WALL_SIGN));
	items.put("sproom_sign", new SignItem(sign, SPROOM_SIGN, SPROOM_WALL_SIGN));
	items.put("stortreean_sign", new SignItem(sign, STORTREEAN_SIGN, STORTREEAN_WALL_SIGN));
	items.put("stroomean_sign", new SignItem(sign, STROOMEAN_SIGN, STROOMEAN_WALL_SIGN));
	items.put("sunrise_fungus_sign", new SignItem(sign, SUNRISE_FUNGUS_SIGN, SUNRISE_FUNGUS_WALL_SIGN));
	items.put("teak_sign", new SignItem(sign, TEAK_SIGN, TEAK_WALL_SIGN));
	items.put("thundercloud_plum_sign", new SignItem(sign, THUNDERCLOUD_PLUM_SIGN, THUNDERCLOUD_PLUM_WALL_SIGN));

	for (WoodType woodType : WoodType.values()) {
	    Item.Properties props = new Item.Properties().group(GENERAL).maxStackSize(1);

	    items.put(woodType + "_boat", new BoatItemDT(woodType, props));
	}

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

	for (Entry<String, Item> entry : items.entrySet()) {
	    ResourceLocation id = new ResourceLocation(MODID, entry.getKey());
	    Item item = entry.getValue();

	    register.register(item.setRegistryName(id));
	}

    }

    @SubscribeEvent
    public static void registerTileEntities(Register<TileEntityType<?>> event) {
	List<TileEntityType<?>> types = new ArrayList<>();

	TileEntityType.Builder<SignTileEntityDT> sign = TileEntityType.Builder.create(SignTileEntityDT::new,
		ALMOND_SIGN, ALMOND_WALL_SIGN, ANAMEATA_SIGN, ANAMEATA_WALL_SIGN, AQUEAN_SIGN, AQUEAN_WALL_SIGN,
		BARSHROOKLE_SIGN, BARSHROOKLE_WALL_SIGN, BLACK_BIRCH_SIGN, BLACK_BIRCH_WALL_SIGN, CHERRY_SIGN,
		CHERRY_WALL_SIGN, COOK_PINE_SIGN, COOK_PINE_WALL_SIGN, CROLOOD_SIGN, CROLOOD_WALL_SIGN,
		DARK_CROLOOD_SIGN, DARK_CROLOOD_WALL_SIGN, EBONY_SIGN, EBONY_WALL_SIGN, FUNERANITE_SIGN,
		FUNERANITE_WALL_SIGN, FUCHSITRA_SIGN, FUCHSITRA_WALL_SIGN, GHOSHROOM_SIGN, GHOSHROOM_WALL_SIGN,
		PELTOGYNE_SIGN, PELTOGYNE_WALL_SIGN, PIN_CHERRY_SIGN, PIN_CHERRY_WALL_SIGN, PLUM_SIGN, PLUM_WALL_SIGN,
		PURFUNGA_SIGN, PURFUNGA_WALL_SIGN, SPROOM_SIGN, SPROOM_WALL_SIGN, STORTREEAN_SIGN, STORTREEAN_WALL_SIGN,
		STROOMEAN_SIGN, STROOMEAN_WALL_SIGN, SUNRISE_FUNGUS_SIGN, SUNRISE_FUNGUS_WALL_SIGN, TEAK_SIGN,
		TEAK_WALL_SIGN, THUNDERCLOUD_PLUM_SIGN, THUNDERCLOUD_PLUM_WALL_SIGN);

	TileEntityType.Builder<StaspNestTileEntity> staspNest = TileEntityType.Builder.create(StaspNestTileEntity::new,
		STASP_NEST);

	types.add(sign.build(null).setRegistryName(new ResourceLocation(MODID, "sign")));
	types.add(staspNest.build(null).setRegistryName(new ResourceLocation(MODID, "stasp_nest")));

	types.forEach(event.getRegistry()::register);
    }
}
