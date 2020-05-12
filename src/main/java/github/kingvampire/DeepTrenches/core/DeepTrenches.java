package github.kingvampire.DeepTrenches.core;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

import github.kingvampire.DeepTrenches.api.capabilities.age.Age;
import github.kingvampire.DeepTrenches.api.capabilities.age.AgeStorage;
import github.kingvampire.DeepTrenches.api.capabilities.age.IAge;
import github.kingvampire.DeepTrenches.api.capabilities.anger.Anger;
import github.kingvampire.DeepTrenches.api.capabilities.anger.AngerStorage;
import github.kingvampire.DeepTrenches.api.capabilities.anger.IAnger;
import github.kingvampire.DeepTrenches.api.loot_tables.conditions.CheckWoodType;
import github.kingvampire.DeepTrenches.core.entity.AdaiggerEntity;
import github.kingvampire.DeepTrenches.core.entity.BoatEntityDT;
import github.kingvampire.DeepTrenches.core.entity.SignTileEntityDT;
import github.kingvampire.DeepTrenches.core.entity.StaspEntity;
import github.kingvampire.DeepTrenches.core.entity.renderer.AdaiggerRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.BoatRendererDT;
import github.kingvampire.DeepTrenches.core.entity.renderer.SignTileEntityDTRenderer;
import github.kingvampire.DeepTrenches.core.entity.renderer.StaspRenderer;
import github.kingvampire.DeepTrenches.core.proxy.ClientProxy;
import github.kingvampire.DeepTrenches.core.proxy.CommonProxy;
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
	ClientRegistry.bindTileEntitySpecialRenderer(SignTileEntityDT.class, new SignTileEntityDTRenderer());

	RenderingRegistry.registerEntityRenderingHandler(AdaiggerEntity.class, AdaiggerRenderer::new);
	RenderingRegistry.registerEntityRenderingHandler(BoatEntityDT.class, BoatRendererDT::new);
	RenderingRegistry.registerEntityRenderingHandler(StaspEntity.class, StaspRenderer::new);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
	CapabilityManager.INSTANCE.register(IAge.class, new AgeStorage(), Age::new);
	CapabilityManager.INSTANCE.register(IAnger.class, new AngerStorage(), Anger::new);
    }

    private void loadComplete(FMLLoadCompleteEvent event) {
	LootConditionManager.registerCondition(new CheckWoodType.Serializer());

	proxy.init();
    }

}
