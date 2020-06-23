package github.kingvampire.DeepTrenches.core.util.registry;

import static github.kingvampire.DeepTrenches.api.enums.WoodType.ALMOND;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.ANAMEATA;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.AQUEAN;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.BARSHROOKLE;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.BLACK_BIRCH;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.CHERRY;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.COOK_PINE;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.CROLOOD;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.DARK_CROLOOD;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.EBONY;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.FUCHSITRA;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.FUNERANITE;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.GHOSHROOM;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.PELTOGYNE;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.PIN_CHERRY;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.PLUM;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.PURFUNGA;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.SPROOM;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.STORTREEAN;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.STROOMEAN;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.SUNRISE_FUNGUS;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.TEAK;
import static github.kingvampire.DeepTrenches.api.enums.WoodType.THUNDERCLOUD_PLUM;
import static github.kingvampire.DeepTrenches.core.init.ItemGroups.GENERAL;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ALMOND_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ALMOND_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ANAMEATA_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.ANAMEATA_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.AQUEAN_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BARSHROOKLE_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BARSHROOKLE_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BLACK_BIRCH_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.BLACK_BIRCH_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CHERRY_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CHERRY_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.COOK_PINE_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.COOK_PINE_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CROLOOD_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.CROLOOD_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.DARK_CROLOOD_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.DARK_CROLOOD_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.EBONY_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.EBONY_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUCHSITRA_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUNERANITE_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.FUNERANITE_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.GHOSHROOM_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.GHOSHROOM_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PELTOGYNE_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PELTOGYNE_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PIN_CHERRY_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PIN_CHERRY_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PLUM_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PLUM_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PURFUNGA_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.PURFUNGA_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SPROOM_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SPROOM_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STORTREEAN_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STORTREEAN_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STROOMEAN_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.STROOMEAN_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SUNRISE_FUNGUS_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.SUNRISE_FUNGUS_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.TEAK_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.TEAK_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.THUNDERCLOUD_PLUM_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModBlocks.THUNDERCLOUD_PLUM_WALL_SIGN;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BARBELED_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BLACK_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.DEEP_LAKE_BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.GIANT_HATCHETFISH;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.LIGHT_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.SMALLTOOTH_DRAGONFISH;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.STASP;
import static github.kingvampire.DeepTrenches.core.init.ModFoods.COOKED_GIANT_HATCHETFISH;
import static github.kingvampire.DeepTrenches.core.init.ModFoods.STORCEAN_FISH;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.item.Foods.SWEET_BERRIES;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import github.kingvampire.DeepTrenches.core.init.ModFoods;
import github.kingvampire.DeepTrenches.core.items.AdaiggerItem;
import github.kingvampire.DeepTrenches.core.items.BoatItemDT;
import github.kingvampire.DeepTrenches.core.items.ModFishBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.SignItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(bus = MOD)
public class ItemRegistry {

    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
	IForgeRegistry<Item> registry = event.getRegistry();

	Properties props = new Properties().group(GENERAL);

	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "cyan_bioluminescent_goo")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "light_blue_bioluminescent_goo")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "green_bioluminescent_goo")));

	Properties apple = new Properties().group(GENERAL).food(SWEET_BERRIES);
	Properties sweetBerries = new Properties().group(GENERAL).food(SWEET_BERRIES);
	
	registry.register(new Item(sweetBerries).setRegistryName(new ResourceLocation(MODID, "almond")));
	registry.register(new Item(sweetBerries).setRegistryName(new ResourceLocation(MODID, "almond_drupe")));
	registry.register(new Item(sweetBerries).setRegistryName(new ResourceLocation(MODID, "cherry")));
	registry.register(new Item(apple).setRegistryName(new ResourceLocation(MODID, "crolood_fruit")));
	registry.register(new Item(apple).setRegistryName(new ResourceLocation(MODID, "dark_crolood_fruit")));
	registry.register(new Item(apple).setRegistryName(new ResourceLocation(MODID, "persimmon_fruit")));
	registry.register(new Item(sweetBerries).setRegistryName(new ResourceLocation(MODID, "pin_cherry")));
	registry.register(new Item(apple).setRegistryName(new ResourceLocation(MODID, "plum")));
	registry.register(new Item(apple).setRegistryName(new ResourceLocation(MODID, "thundercloud_plum")));

	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "aquean_sap")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "bottle_of_aquean_sap")));
	registry.register(new AdaiggerItem(props).setRegistryName(new ResourceLocation(MODID, "adaigger")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "gyldelion_dye")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "gyldelion_ingot")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "gyldelion_nugget")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "gyldelion_stick")));

	Properties storceanFish = new Properties().group(GENERAL).food(STORCEAN_FISH);
	Properties cookedGiantHatchetfish = new Properties().food(COOKED_GIANT_HATCHETFISH).group(GENERAL);
	Properties giantHatchetfish = new Properties().food(ModFoods.GIANT_HATCHETFISH).group(GENERAL);
	
	registry.register(new Item(storceanFish).setRegistryName(new ResourceLocation(MODID, "barbeled_loosejaw")));
	registry.register(new Item(storceanFish).setRegistryName(new ResourceLocation(MODID,"betta")));
	registry.register(new Item(storceanFish).setRegistryName(new ResourceLocation(MODID,"black_loosejaw")));
	registry.register(new Item(storceanFish).setRegistryName(new ResourceLocation(MODID,"deep_lake_betta")));
	registry.register(new Item(cookedGiantHatchetfish).setRegistryName(new ResourceLocation(MODID,"cooked_giant_hatchetfish")));
	registry.register(new Item(giantHatchetfish).setRegistryName(new ResourceLocation(MODID,"giant_hatchetfish")));
	registry.register(new Item(storceanFish).setRegistryName(new ResourceLocation(MODID,"light_loosejaw")));
	registry.register(new Item(storceanFish).setRegistryName(new ResourceLocation(MODID, "smalltooth_dragonfish")));

	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "loosejaw_tooth")));

	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "almond_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "anameata_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "aquean_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "barshrookle_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "black_birch_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "cherry_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "cook_pine_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "crolood_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "dark_crolood_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "ebony_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "funeranite_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "fuchsitra_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "ghoshroom_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "peltogyne_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "pin_cherry_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "plum_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "purfunga_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "sproom_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "stortreean_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "stroomean_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "sunrise_fungus_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "teak_stick")));
	registry.register(new Item(props).setRegistryName(new ResourceLocation(MODID, "thundercloud_plum_stick")));
	
	Properties single = new Properties().maxStackSize(1).group(GENERAL);
	
	registry.register(new ModFishBucketItem(BARBELED_LOOSEJAW, single).setRegistryName(new ResourceLocation(MODID, "barbeled_loosejaw_bucket")));
	registry.register(new ModFishBucketItem(BETTA, single).setRegistryName(new ResourceLocation(MODID, "betta_bucket")));
	registry.register(new ModFishBucketItem(BLACK_LOOSEJAW, single).setRegistryName(new ResourceLocation(MODID, "black_loosejaw_bucket")));
	registry.register(new ModFishBucketItem(DEEP_LAKE_BETTA, single).setRegistryName(new ResourceLocation(MODID, "deep_lake_betta_bucket")));
	registry.register(new ModFishBucketItem(GIANT_HATCHETFISH, single).setRegistryName(new ResourceLocation(MODID, "giant_hatchetfish_bucket")));
	registry.register(new ModFishBucketItem(LIGHT_LOOSEJAW, single).setRegistryName(new ResourceLocation(MODID, "light_loosejaw_bucket")));
	registry.register(new ModFishBucketItem(SMALLTOOTH_DRAGONFISH, single).setRegistryName(new ResourceLocation(MODID, "smalltooth_dragonfish_bucket")));

	registry.register(new SpawnEggItem(BARBELED_LOOSEJAW, 921113, 15859744, single).setRegistryName(new ResourceLocation(MODID, "barbeled_loosejaw_spawn_egg")));
	registry.register(new SpawnEggItem(BETTA, 7347502, 9183521, single).setRegistryName(new ResourceLocation(MODID, "betta_spawn_egg")));
	registry.register(new SpawnEggItem(BLACK_LOOSEJAW, 2102566, 11010053, single).setRegistryName(new ResourceLocation(MODID, "black_loosejaw_spawn_egg")));
	registry.register(new SpawnEggItem(DEEP_LAKE_BETTA, 1189390, 5013319, single).setRegistryName(new ResourceLocation(MODID, "deep_lake_betta_spawn_egg")));
	registry.register(new SpawnEggItem(GIANT_HATCHETFISH, 9870757, 12311039, single).setRegistryName(new ResourceLocation(MODID, "giant_hatchetfish_spawn_egg")));
	registry.register(new SpawnEggItem(LIGHT_LOOSEJAW, 1643048, 4836351, single).setRegistryName(new ResourceLocation(MODID, "light_loosejaw_spawn_egg")));
	registry.register(new SpawnEggItem(STASP, 2695792, 5124510, single).setRegistryName(new ResourceLocation(MODID, "stasp_spawn_egg")));
	registry.register(new SpawnEggItem(SMALLTOOTH_DRAGONFISH, 1250598, 16728832, single).setRegistryName(new ResourceLocation(MODID, "smalltooth_dragonfish_spawn_egg")));

	Item.Properties sign = new Item.Properties().group(GENERAL).maxStackSize(16);

	registry.register(new SignItem(sign, ALMOND_SIGN, ALMOND_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "almond_sign")));
	registry.register(new SignItem(sign, ANAMEATA_SIGN, ANAMEATA_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "anameata_sign")));
	registry.register(new SignItem(sign, AQUEAN_SIGN, AQUEAN_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "aquean_sign")));
	registry.register(new SignItem(sign, BARSHROOKLE_SIGN, BARSHROOKLE_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "barshrookle_sign")));
	registry.register(new SignItem(sign, BLACK_BIRCH_SIGN, BLACK_BIRCH_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "black_birch_sign")));
	registry.register(new SignItem(sign, CHERRY_SIGN, CHERRY_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "cherry_sign")));
	registry.register(new SignItem(sign, COOK_PINE_SIGN, COOK_PINE_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "cook_pine_sign")));
	registry.register(new SignItem(sign, CROLOOD_SIGN, CROLOOD_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "crolood_sign")));
	registry.register(new SignItem(sign, DARK_CROLOOD_SIGN, DARK_CROLOOD_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "dark_crolood_sign")));
	registry.register(new SignItem(sign, EBONY_SIGN, EBONY_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "ebony_sign")));
	registry.register(new SignItem(sign, FUNERANITE_SIGN, FUNERANITE_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "funeranite_sign")));
	registry.register(new SignItem(sign, FUCHSITRA_SIGN, FUCHSITRA_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "fuchsitra_sign")));
	registry.register(new SignItem(sign, GHOSHROOM_SIGN, GHOSHROOM_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "ghoshroom_sign")));
	registry.register(new SignItem(sign, PELTOGYNE_SIGN, PELTOGYNE_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "peltogyne_sign")));
	registry.register(new SignItem(sign, PIN_CHERRY_SIGN, PIN_CHERRY_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "pin_cherry_sign")));
	registry.register(new SignItem(sign, PLUM_SIGN, PLUM_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "plum_sign")));
	registry.register(new SignItem(sign, PURFUNGA_SIGN, PURFUNGA_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "purfunga_sign")));
	registry.register(new SignItem(sign, SPROOM_SIGN, SPROOM_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "sproom_sign")));
	registry.register(new SignItem(sign, STORTREEAN_SIGN, STORTREEAN_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "stortreean_sign")));
	registry.register(new SignItem(sign, STROOMEAN_SIGN, STROOMEAN_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "stroomean_sign")));
	registry.register(new SignItem(sign, SUNRISE_FUNGUS_SIGN, SUNRISE_FUNGUS_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "sunrise_fungus_sign")));
	registry.register(new SignItem(sign, TEAK_SIGN, TEAK_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "teak_sign")));
	registry.register(new SignItem(sign, THUNDERCLOUD_PLUM_SIGN, THUNDERCLOUD_PLUM_WALL_SIGN).setRegistryName(new ResourceLocation(MODID, "thundercloud_plum_sign")));

	registry.register(new BoatItemDT(ALMOND, sign).setRegistryName(new ResourceLocation(MODID, "almond_boat")));
	registry.register(new BoatItemDT(ANAMEATA, sign).setRegistryName(new ResourceLocation(MODID, "anameata_boat")));
	registry.register(new BoatItemDT(AQUEAN, sign).setRegistryName(new ResourceLocation(MODID, "aquean_boat")));
	registry.register(new BoatItemDT(BARSHROOKLE, sign).setRegistryName(new ResourceLocation(MODID, "barshrookle_boat")));
	registry.register(new BoatItemDT(BLACK_BIRCH, sign).setRegistryName(new ResourceLocation(MODID,"black_birch_boat")));
	registry.register(new BoatItemDT(CHERRY, sign).setRegistryName(new ResourceLocation(MODID,"cherry_boat")));
	registry.register(new BoatItemDT(COOK_PINE, sign).setRegistryName(new ResourceLocation(MODID,"cook_pine_boat")));
	registry.register(new BoatItemDT(CROLOOD, sign).setRegistryName(new ResourceLocation(MODID, "crolood_boat")));
	registry.register(new BoatItemDT(DARK_CROLOOD, sign).setRegistryName(new ResourceLocation(MODID,"dark_crolood_boat")));
	registry.register(new BoatItemDT(EBONY, sign).setRegistryName(new ResourceLocation(MODID, "ebony_boat")));
	registry.register(new BoatItemDT(FUNERANITE, sign).setRegistryName(new ResourceLocation(MODID, "funeranite_boat")));
	registry.register(new BoatItemDT(FUCHSITRA, sign).setRegistryName(new ResourceLocation(MODID, "fuchsitra_boat")));
	registry.register(new BoatItemDT(GHOSHROOM, sign).setRegistryName(new ResourceLocation(MODID, "ghoshroom_boat")));
	registry.register(new BoatItemDT(PELTOGYNE, sign).setRegistryName(new ResourceLocation(MODID, "peltogyne_boat")));
	registry.register(new BoatItemDT(PIN_CHERRY, sign).setRegistryName(new ResourceLocation(MODID, "pin_cherry_boat")));
	registry.register(new BoatItemDT(PLUM, sign).setRegistryName(new ResourceLocation(MODID, "plum_boat")));
	registry.register(new BoatItemDT(PURFUNGA, sign).setRegistryName(new ResourceLocation(MODID, "purfunga_boat")));
	registry.register(new BoatItemDT(SPROOM, sign).setRegistryName(new ResourceLocation(MODID, "sproom_boat")));
	registry.register(new BoatItemDT(STORTREEAN, sign).setRegistryName(new ResourceLocation(MODID, "stortreean_boat")));
	registry.register(new BoatItemDT(STROOMEAN, sign).setRegistryName(new ResourceLocation(MODID, "stroomean_boat")));
	registry.register(new BoatItemDT(SUNRISE_FUNGUS, sign).setRegistryName(new ResourceLocation(MODID, "sunrise_fungus_boat")));
	registry.register(new BoatItemDT(TEAK, sign).setRegistryName(new ResourceLocation(MODID, "teak_boat")));
	registry.register(new BoatItemDT(THUNDERCLOUD_PLUM, sign).setRegistryName(new ResourceLocation(MODID, "thundercloud_plum_boat")));

    }

}
