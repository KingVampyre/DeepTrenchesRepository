package github.kingvampire.DeepTrenches.core.util.registry;

import static github.kingvampire.DeepTrenches.core.init.ModTaxons.STORCEANOVESPIIDAE;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.APOPLIXIOVESPA;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.ARGYROPELECUS;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.ARGYROPELECUS_GIGAS;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.BARBELED_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.BETTA_SPLENDIDENS;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.BETTA_TRELOSIAGNUS;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.BLACK_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.LIGHT_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.MALACOSTEUS;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.OSPHRONEMIDAE;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.PACHYSTOMIAS;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.PACHYSTOMIAS_MICRODON;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.PERCIFORMES;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.STERNOPTYCHIDAE;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.STOMIIDAE;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.STOMIIFORMES;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.STORCEANOVESPIIFORMES;
import static github.kingvampire.DeepTrenches.core.init.ModTaxons.APOPLIXIOVESPA_SPECIES;
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

	Rank barbeledLoosejaw = new Rank.Builder()
		.order(STOMIIFORMES)
		.family(STOMIIDAE)
		.genus(MALACOSTEUS)
		.species(BARBELED_LOOSEJAW)
		.build(new ResourceLocation(MODID, "barbeled_loosejaw"));
	
	Rank betta = new Rank.Builder()
		.order(PERCIFORMES)
		.family(OSPHRONEMIDAE)
		.genus(BETTA)
		.species(BETTA_SPLENDIDENS)
		.build(new ResourceLocation(MODID, "betta"));
	
	Rank blackLoosejaw = new Rank.Builder()
		.order(STOMIIFORMES)
		.family(STOMIIDAE)
		.genus(MALACOSTEUS)
		.species(BLACK_LOOSEJAW)
		.build(new ResourceLocation(MODID, "black_loosejaw"));

	Rank deepLakeBetta = new Rank.Builder()
		.order(PERCIFORMES)
		.family(OSPHRONEMIDAE)
		.genus(BETTA)
		.species(BETTA_TRELOSIAGNUS)
		.build(new ResourceLocation(MODID, "deep_lake_betta"));
	
	Rank giantHatchetfish = new Rank.Builder()
		.order(STOMIIFORMES)
		.family(STERNOPTYCHIDAE)
		.genus(ARGYROPELECUS)
		.species(ARGYROPELECUS_GIGAS)
		.build(new ResourceLocation(MODID, "giant_hatchetfish"));

	Rank lightLoosejaw = new Rank.Builder()
		.order(STOMIIFORMES)
		.family(STOMIIDAE)
		.genus(MALACOSTEUS)
		.species(LIGHT_LOOSEJAW)
		.build(new ResourceLocation(MODID, "light_loosejaw"));

	Rank smalltoothDragonfish = new Rank.Builder()
		.order(STOMIIFORMES)
		.family(STOMIIDAE)
		.genus(PACHYSTOMIAS)
		.species(PACHYSTOMIAS_MICRODON)
		.build(new ResourceLocation(MODID, "smalltooth_dragonfish"));
	
	Rank stasp = new Rank.Builder()
		.order(STORCEANOVESPIIFORMES)
		.family(STORCEANOVESPIIDAE)
		.genus(APOPLIXIOVESPA)
		.species(APOPLIXIOVESPA_SPECIES)
		.build(new ResourceLocation(MODID, "stasp"));

	registry.registerAll(barbeledLoosejaw, betta, deepLakeBetta, blackLoosejaw, giantHatchetfish, lightLoosejaw, smalltoothDragonfish, stasp);

    }

}
