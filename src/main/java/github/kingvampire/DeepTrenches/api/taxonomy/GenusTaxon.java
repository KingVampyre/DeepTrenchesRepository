package github.kingvampire.DeepTrenches.api.taxonomy;

import static github.kingvampire.DeepTrenches.api.taxonomy.TaxonType.GENUS;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.util.ResourceLocation;

public class GenusTaxon extends Taxon {

    private List<SpeciesTaxon> species = Lists.newArrayList();

    public GenusTaxon(String scientificName) {
	super(GENUS, scientificName);

	this.setRegistryName(new ResourceLocation(MODID, scientificName));
    }

    public GenusTaxon(String scientificName, String vulgarName) {
	super(GENUS, scientificName, vulgarName);

	this.setRegistryName(new ResourceLocation(MODID, scientificName));
    }

    public GenusTaxon addSpecies(SpeciesTaxon... species) {
	this.species.addAll(Arrays.asList(species));

	return this;
    }

    public List<SpeciesTaxon> getSpecies() {
	return this.species;
    }

}
