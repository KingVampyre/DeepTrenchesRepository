package github.kingvampire.DeepTrenches.core.util.registry;

import static github.kingvampire.DeepTrenches.core.init.ModTaxons.BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.BETTA_SPLENDIDENS;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.BETTA_TRELOSIAGNUS;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.OSPHRONEMIDAE;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.PERCIFORMES;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import github.kingvampire.DeepTrenches.api.taxonomy.Rank;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(bus = MOD)
public class RankRegistry {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void register(RegistryEvent.Register<Rank> event) {
	IForgeRegistry<Rank> registry = event.getRegistry();

	Rank betta = new Rank.Builder()
		.order(PERCIFORMES)
		.family(OSPHRONEMIDAE)
		.genus(BETTA)
		.species(BETTA_SPLENDIDENS)
		.build(new ResourceLocation(MODID, "betta"));

	Rank deepLakeBetta = new Rank.Builder()
		.order(PERCIFORMES)
		.family(OSPHRONEMIDAE)
		.genus(BETTA)
		.species(BETTA_TRELOSIAGNUS)
		.build(new ResourceLocation(MODID, "deep_lake_betta"));
	
	registry.registerAll(betta, deepLakeBetta);
    }

}
