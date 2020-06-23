package github.kingvampire.DeepTrenches.core;

import static github.kingvampire.DeepTrenches.core.init.ModColorMaps.STORCEAN_FOLIAGE;
import static github.kingvampire.DeepTrenches.core.init.ModColorMaps.STORCEAN_MOSOIL;
import static github.kingvampire.DeepTrenches.core.init.ModColorMaps.STORCEAN_WATER;
import static github.kingvampire.DeepTrenches.core.init.ModItems.LOOSEJAW_TOOTH;
import static github.kingvampire.DeepTrenches.core.init.ModPotions.LONG_SOFTBONES;
import static github.kingvampire.DeepTrenches.core.init.ModPotions.LONG_STRONG_SOFTBONES;
import static github.kingvampire.DeepTrenches.core.init.ModPotions.SOFTBONES;
import static github.kingvampire.DeepTrenches.core.init.ModPotions.STRONG_SOFTBONES;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraft.item.Items.DRAGON_BREATH;
import static net.minecraft.item.Items.GLOWSTONE_DUST;
import static net.minecraft.item.Items.GUNPOWDER;
import static net.minecraft.item.Items.LINGERING_POTION;
import static net.minecraft.item.Items.POTION;
import static net.minecraft.item.Items.REDSTONE;
import static net.minecraft.item.Items.SPLASH_POTION;
import static net.minecraft.potion.Potions.AWKWARD;
import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

import java.io.IOException;

import github.kingvampire.DeepTrenches.api.capabilities.age.Age;
import github.kingvampire.DeepTrenches.api.capabilities.age.AgeStorage;
import github.kingvampire.DeepTrenches.api.capabilities.age.IAge;
import github.kingvampire.DeepTrenches.api.capabilities.anger.Anger;
import github.kingvampire.DeepTrenches.api.capabilities.anger.AngerStorage;
import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.capabilities.breed.Breed;
import github.kingvampire.DeepTrenches.api.capabilities.breed.BreedStorage;
import github.kingvampire.DeepTrenches.api.capabilities.breed.IBreed;
import github.kingvampire.DeepTrenches.api.capabilities.group.Group;
import github.kingvampire.DeepTrenches.api.capabilities.group.GroupStorage;
import github.kingvampire.DeepTrenches.api.capabilities.group.IGroup;
import github.kingvampire.DeepTrenches.api.capabilities.lit.ILit;
import github.kingvampire.DeepTrenches.api.capabilities.lit.Lit;
import github.kingvampire.DeepTrenches.api.capabilities.lit.LitStorage;
import github.kingvampire.DeepTrenches.api.capabilities.pollen.IPollen;
import github.kingvampire.DeepTrenches.api.capabilities.pollen.Pollen;
import github.kingvampire.DeepTrenches.api.capabilities.pollen.PollenStorage;
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.capabilities.tame.Tame;
import github.kingvampire.DeepTrenches.api.capabilities.tame.TameStorage;
import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.ITaxonomy;
import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.Taxonomy;
import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyStorage;
import github.kingvampire.DeepTrenches.api.entity.tileentity.ModBoatEntity;
import github.kingvampire.DeepTrenches.api.entity.tileentity.ModSignTileEntity;
import github.kingvampire.DeepTrenches.api.entity.tileentity.renderer.ModBoatRenderer;
import github.kingvampire.DeepTrenches.api.entity.tileentity.renderer.ModSignTileEntityRenderer;
import github.kingvampire.DeepTrenches.api.loot_tables.conditions.CheckWoodType;
import github.kingvampire.DeepTrenches.core.brewing.ModBrewingRecipe;
import github.kingvampire.DeepTrenches.core.entity.AdaiggerEntity;
import github.kingvampire.DeepTrenches.core.entity.BarbeledLoosejawEntity;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import github.kingvampire.DeepTrenches.core.entity.BlackLoosejawEntity;
import github.kingvampire.DeepTrenches.core.entity.DeepLakeBettaEntity;
import github.kingvampire.DeepTrenches.core.entity.GiantHatchetfishEntity;
import github.kingvampire.DeepTrenches.core.entity.LightLoosejawEntity;
import github.kingvampire.DeepTrenches.core.entity.SmalltoothDragonfishEntity;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import github.kingvampire.DeepTrenches.core.entity.renderer.AdaiggerRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.BettaRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.DeepLakeBettaRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.GiantHatchetfishRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.StaspRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.dragonfishes.BarbeledLoosejawRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.dragonfishes.BlackLoosejawRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.dragonfishes.LightLoosejawRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.dragonfishes.SmalltoothDragonfishRenderer;
import github.kingvampire.DeepTrenches.core.proxy.ClientProxy;
import github.kingvampire.DeepTrenches.core.proxy.CommonProxy;
import github.kingvampire.DeepTrenches.core.util.NetworkHandler;
import github.kingvampire.DeepTrenches.core.util.packets.CapabilityPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.ColorMapLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("deprecation")
@Mod(MODID)
public class DeepTrenches {

    public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public DeepTrenches() {
	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);

	EVENT_BUS.register(this);
    }

    private void clientSetup(FMLClientSetupEvent event) {
	ClientRegistry.bindTileEntitySpecialRenderer(ModSignTileEntity.class, new ModSignTileEntityRenderer());

	RenderingRegistry.registerEntityRenderingHandler(AdaiggerEntity.class, AdaiggerRenderer::new);
	RenderingRegistry.registerEntityRenderingHandler(BettaEntity.class, BettaRenderer::new);
	RenderingRegistry.registerEntityRenderingHandler(BlackLoosejawEntity.class, BlackLoosejawRenderer::new);
	RenderingRegistry.registerEntityRenderingHandler(ModBoatEntity.class, ModBoatRenderer::new);
	RenderingRegistry.registerEntityRenderingHandler(DeepLakeBettaEntity.class, DeepLakeBettaRenderer::new);
	RenderingRegistry.registerEntityRenderingHandler(GiantHatchetfishEntity.class, GiantHatchetfishRenderer::new);
	RenderingRegistry.registerEntityRenderingHandler(LightLoosejawEntity.class, LightLoosejawRenderer::new);
	RenderingRegistry.registerEntityRenderingHandler(BarbeledLoosejawEntity.class, BarbeledLoosejawRenderer::new);
	RenderingRegistry.registerEntityRenderingHandler(SmalltoothDragonfishEntity.class,
		SmalltoothDragonfishRenderer::new);
	RenderingRegistry.registerEntityRenderingHandler(StaspEntity.class, StaspRenderer::new);

	Minecraft minecraft = event.getMinecraftSupplier().get();
	IResourceManager resourceManager = minecraft.getResourceManager();

	try {
	    ResourceLocation storceanFoliageId = new ResourceLocation(MODID, "textures/colormap/storcean_foliage.png");
	    ResourceLocation storceanMosoildId = new ResourceLocation(MODID, "textures/colormap/storcean_mosoil.png");
	    ResourceLocation storceanWaterId = new ResourceLocation(MODID, "textures/colormap/storcean_water.png");

	    int[] storceanFoliage = ColorMapLoader.loadColors(resourceManager, storceanFoliageId);
	    int[] storceanMosoild = ColorMapLoader.loadColors(resourceManager, storceanMosoildId);
	    int[] storceanWater = ColorMapLoader.loadColors(resourceManager, storceanWaterId);

	    STORCEAN_FOLIAGE.setColorizer(storceanFoliage);
	    STORCEAN_MOSOIL.setColorizer(storceanMosoild);
	    STORCEAN_WATER.setColorizer(storceanWater);

	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
	DeferredWorkQueue.runLater(() -> {

	    BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(
		    Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(POTION), AWKWARD)),
		    LOOSEJAW_TOOTH, PotionUtils.addPotionToItemStack(new ItemStack(POTION), SOFTBONES)));

	    BrewingRecipeRegistry.addRecipe(
		    Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(POTION), SOFTBONES)),
		    Ingredient.fromItems(GUNPOWDER),
		    PotionUtils.addPotionToItemStack(new ItemStack(SPLASH_POTION), SOFTBONES));

	    BrewingRecipeRegistry.addRecipe(
		    Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(POTION), LONG_SOFTBONES)),
		    Ingredient.fromItems(GUNPOWDER),
		    PotionUtils.addPotionToItemStack(new ItemStack(SPLASH_POTION), LONG_SOFTBONES));

	    BrewingRecipeRegistry.addRecipe(
		    Ingredient
			    .fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(POTION), LONG_STRONG_SOFTBONES)),
		    Ingredient.fromItems(GUNPOWDER),
		    PotionUtils.addPotionToItemStack(new ItemStack(SPLASH_POTION), LONG_STRONG_SOFTBONES));

	    BrewingRecipeRegistry.addRecipe(
		    Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(POTION), STRONG_SOFTBONES)),
		    Ingredient.fromItems(GUNPOWDER),
		    PotionUtils.addPotionToItemStack(new ItemStack(SPLASH_POTION), STRONG_SOFTBONES));

	    BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(
		    Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(POTION), SOFTBONES)), REDSTONE,
		    PotionUtils.addPotionToItemStack(new ItemStack(POTION), LONG_SOFTBONES)));

	    BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(
		    Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(POTION), STRONG_SOFTBONES)),
		    REDSTONE, PotionUtils.addPotionToItemStack(new ItemStack(POTION), LONG_STRONG_SOFTBONES)));

	    BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(
		    Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(POTION), LONG_SOFTBONES)),
		    GLOWSTONE_DUST, PotionUtils.addPotionToItemStack(new ItemStack(POTION), LONG_STRONG_SOFTBONES)));

	    BrewingRecipeRegistry.addRecipe(new ModBrewingRecipe(
		    Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(POTION), SOFTBONES)),
		    GLOWSTONE_DUST, PotionUtils.addPotionToItemStack(new ItemStack(POTION), STRONG_SOFTBONES)));

	    BrewingRecipeRegistry.addRecipe(
		    Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(SPLASH_POTION), SOFTBONES)),
		    Ingredient.fromItems(DRAGON_BREATH),
		    PotionUtils.addPotionToItemStack(new ItemStack(LINGERING_POTION), SOFTBONES));

	    BrewingRecipeRegistry.addRecipe(
		    Ingredient
			    .fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(SPLASH_POTION), LONG_SOFTBONES)),
		    Ingredient.fromItems(DRAGON_BREATH),
		    PotionUtils.addPotionToItemStack(new ItemStack(LINGERING_POTION), LONG_SOFTBONES));

	    BrewingRecipeRegistry.addRecipe(
		    Ingredient.fromStacks(
			    PotionUtils.addPotionToItemStack(new ItemStack(SPLASH_POTION), LONG_STRONG_SOFTBONES)),
		    Ingredient.fromItems(DRAGON_BREATH),
		    PotionUtils.addPotionToItemStack(new ItemStack(LINGERING_POTION), LONG_STRONG_SOFTBONES));

	    BrewingRecipeRegistry.addRecipe(
		    Ingredient.fromStacks(
			    PotionUtils.addPotionToItemStack(new ItemStack(SPLASH_POTION), STRONG_SOFTBONES)),
		    Ingredient.fromItems(DRAGON_BREATH),
		    PotionUtils.addPotionToItemStack(new ItemStack(LINGERING_POTION), STRONG_SOFTBONES));
	});

	CapabilityManager.INSTANCE.register(IAge.class, new AgeStorage(), Age::new);
	CapabilityManager.INSTANCE.register(IAnger.class, new AngerStorage(), Anger::new);
	CapabilityManager.INSTANCE.register(IGroup.class, new GroupStorage(), Group::new);
	CapabilityManager.INSTANCE.register(IBreed.class, new BreedStorage(), Breed::new);
	CapabilityManager.INSTANCE.register(ILit.class, new LitStorage(), Lit::new);
	CapabilityManager.INSTANCE.register(IPollen.class, new PollenStorage(), Pollen::new);
	CapabilityManager.INSTANCE.register(ITame.class, new TameStorage(), Tame::new);
	CapabilityManager.INSTANCE.register(ITaxonomy.class, new TaxonomyStorage(), Taxonomy::new);

	NetworkHandler.INSTANCE.registerMessage(0, CapabilityPacket.class, CapabilityPacket::encode,
		CapabilityPacket::decode, CapabilityPacket::handle);
    }

    private void loadComplete(FMLLoadCompleteEvent event) {
	LootConditionManager.registerCondition(new CheckWoodType.Serializer());

	proxy.init();
    }

}
