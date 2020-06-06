package github.kingvampire.DeepTrenches.core;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

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
import github.kingvampire.DeepTrenches.api.capabilities.tame.ITame;
import github.kingvampire.DeepTrenches.api.capabilities.tame.Tame;
import github.kingvampire.DeepTrenches.api.capabilities.tame.TameStorage;
import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.ITaxonomy;
import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.Taxonomy;
import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyStorage;
import github.kingvampire.DeepTrenches.api.entity.ModBoatEntity;
import github.kingvampire.DeepTrenches.api.entity.ModSignTileEntity;
import github.kingvampire.DeepTrenches.api.entity.renderer.ModBoatRenderer;
import github.kingvampire.DeepTrenches.api.entity.renderer.ModSignTileEntityRenderer;
import github.kingvampire.DeepTrenches.api.loot_tables.conditions.CheckWoodType;
import github.kingvampire.DeepTrenches.core.entity.AdaiggerEntity;
import github.kingvampire.DeepTrenches.core.entity.BettaEntity;
import github.kingvampire.DeepTrenches.core.entity.BoatEntityDT;
import github.kingvampire.DeepTrenches.core.entity.DeepLakeBettaEntity;
import github.kingvampire.DeepTrenches.core.entity.SignTileEntityDT;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import github.kingvampire.DeepTrenches.core.entity.renderer.AdaiggerRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.BettaRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.DeepLakeBettaRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.SignTileEntityDTRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.StaspRenderer;
import github.kingvampire.DeepTrenches.core.proxy.ClientProxy;
import github.kingvampire.DeepTrenches.core.proxy.CommonProxy;
import github.kingvampire.DeepTrenches.core.util.NetworkHandler;
import github.kingvampire.DeepTrenches.core.util.packets.AgeCapabilityPacket;
import github.kingvampire.DeepTrenches.core.util.packets.LitCapabilityPacket;
import github.kingvampire.DeepTrenches.core.util.packets.TaxonomyCapabilityPacket;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
	RenderingRegistry.registerEntityRenderingHandler(StaspEntity.class, StaspRenderer::new);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
	CapabilityManager.INSTANCE.register(IAge.class, new AgeStorage(), Age::new);
	CapabilityManager.INSTANCE.register(IAnger.class, new AngerStorage(), Anger::new);
	CapabilityManager.INSTANCE.register(IGroup.class, new GroupStorage(), Group::new);
	CapabilityManager.INSTANCE.register(IBreed.class, new BreedStorage(), Breed::new);
	CapabilityManager.INSTANCE.register(ILit.class, new LitStorage(), Lit::new);
	CapabilityManager.INSTANCE.register(ITame.class, new TameStorage(), Tame::new);
	CapabilityManager.INSTANCE.register(ITaxonomy.class, new TaxonomyStorage(), Taxonomy::new);

	NetworkHandler.INSTANCE.registerMessage(0, TaxonomyCapabilityPacket.class, TaxonomyCapabilityPacket::encode,
		TaxonomyCapabilityPacket::decode, TaxonomyCapabilityPacket::handle);

	NetworkHandler.INSTANCE.registerMessage(1, LitCapabilityPacket.class, LitCapabilityPacket::encode,
		LitCapabilityPacket::decode, LitCapabilityPacket::handle);

	NetworkHandler.INSTANCE.registerMessage(2, AgeCapabilityPacket.class, AgeCapabilityPacket::encode,
		AgeCapabilityPacket::decode, AgeCapabilityPacket::handle);
    }

    private void loadComplete(FMLLoadCompleteEvent event) {
	LootConditionManager.registerCondition(new CheckWoodType.Serializer());

	proxy.init();
    }

}
