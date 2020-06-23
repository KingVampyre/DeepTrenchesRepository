package github.kingvampire.DeepTrenches.core.taxonomy.species;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import net.minecraft.util.ResourceLocation;

public class ApoplixiovespaSpecies extends SpeciesTaxon {

    public ApoplixiovespaSpecies() {
	super("apoplixiovespa", "stasp");

	SubspeciesTaxon aurantiacopterygius = new SubspeciesTaxon("aurantiacopterygius", "orange_winged");
	SubspeciesTaxon caerulopterygius = new SubspeciesTaxon("caerulopterygius", "blue_winged");
	SubspeciesTaxon obscurus = new SubspeciesTaxon("obscurus", "black");
	SubspeciesTaxon rosea = new SubspeciesTaxon("rosea", "fuchsitra");

	aurantiacopterygius.setRegistryName(new ResourceLocation(MODID, "aurantiacopterygius"));
	caerulopterygius.setRegistryName(new ResourceLocation(MODID, "caerulopterygius"));
	obscurus.setRegistryName(new ResourceLocation(MODID, "obscurus"));
	rosea.setRegistryName(new ResourceLocation(MODID, "rosea"));
	
	this.addSubspecies(aurantiacopterygius, caerulopterygius, obscurus, rosea);
	this.setRegistryName(new ResourceLocation(MODID, "apoplixiovespa_species"));
    }

}
