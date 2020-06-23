package github.kingvampire.DeepTrenches.core.util.registry;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import java.util.List;

import com.google.common.collect.Lists;

import github.kingvampire.DeepTrenches.api.taxonomy.FamilyTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.GenusTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.OrderTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.Taxon;
import github.kingvampire.DeepTrenches.core.taxonomy.genus.BettaGenus;
import github.kingvampire.DeepTrenches.core.taxonomy.genus.BigeyeLoosejawsGenus;
import github.kingvampire.DeepTrenches.core.taxonomy.genus.MalacosteusGenus;
import github.kingvampire.DeepTrenches.core.taxonomy.genus.SilverHatchetfishGenus;
import github.kingvampire.DeepTrenches.core.taxonomy.species.ApoplixiovespaSpecies;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(bus = MOD)
public class TaxonRegistry {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void register(RegistryEvent.Register<Taxon> event) {
	IForgeRegistry<Taxon> registry = event.getRegistry();

	OrderTaxon stomiiformes = new OrderTaxon("stomiiformes");

	// Betta and Deep Lake Betta
	OrderTaxon perciformes = new OrderTaxon("perciformes");
	FamilyTaxon osphronemidae = new FamilyTaxon("osphronemidae", "gouramis");
	GenusTaxon betta = new BettaGenus();

	perciformes.addFamily(osphronemidae.addGenus(betta));
	
	// Giant Hatchetfish, Loosejaws, and Smalltooth Dragonfish
	FamilyTaxon stomiidae = new FamilyTaxon("stomiidae");
	FamilyTaxon sternoptychidae = new FamilyTaxon("sternoptychidae", "hatchetfishes");

	GenusTaxon malacosteus = new MalacosteusGenus();
	GenusTaxon argyropelecus = new SilverHatchetfishGenus();
	GenusTaxon pachystomias = new BigeyeLoosejawsGenus();

	stomiiformes.addFamily(sternoptychidae.addGenus(argyropelecus), stomiidae.addGenus(malacosteus, pachystomias));

	// Stasp
	OrderTaxon storceanovespiiformes = new OrderTaxon("storceanovespiiformes");
	FamilyTaxon storceanovespiidae = new FamilyTaxon("storceanovespiidae");
	GenusTaxon apoplixiovespa = new GenusTaxon("apoplixiovespa", "shockwasps");
	SpeciesTaxon apoplixiovespaSpecies = new ApoplixiovespaSpecies();

	storceanovespiiformes.addFamily(storceanovespiidae.addGenus(apoplixiovespa.addSpecies(apoplixiovespaSpecies)));

	// Register All
	Lists.newArrayList(perciformes, stomiiformes, storceanovespiiformes)
		.stream()
		.peek(registry::register)
		.map(OrderTaxon::getFamilies)
		.flatMap(List::stream)
		.peek(registry::register)
		.map(FamilyTaxon::getGenuses)
		.flatMap(List::stream)
		.peek(registry::register)
		.map(GenusTaxon::getSpecies)
		.flatMap(List::stream)
		.peek(registry::register)
		.map(SpeciesTaxon::getSubspecies)
		.flatMap(List::stream)
		.forEach(registry::register);

    }

}
