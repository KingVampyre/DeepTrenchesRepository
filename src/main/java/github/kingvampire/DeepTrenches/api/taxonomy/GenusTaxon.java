package github.kingvampire.DeepTrenches.api.taxonomy;

import static github.kingvampire.DeepTrenches.api.taxonomy.TaxonType.GENUS;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

public class GenusTaxon extends Taxon {

    private List<SpeciesTaxon> species = Lists.newArrayList();

    public GenusTaxon(String scientificName) {
	super(GENUS, scientificName);
    }

    public GenusTaxon(String scientificName, String vulgarName) {
	super(GENUS, scientificName, vulgarName);
    }

    public GenusTaxon addSpecies(SpeciesTaxon... species) {
	this.species.addAll(Arrays.asList(species));

	return this;
    }

    public List<SpeciesTaxon> getSpecies() {
	return this.species;
    }

}
