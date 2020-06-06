package github.kingvampire.DeepTrenches.api.taxonomy;

import static github.kingvampire.DeepTrenches.api.taxonomy.TaxonType.SPECIES;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

public class SpeciesTaxon extends Taxon {

    private List<SubspeciesTaxon> subspecies = Lists.newArrayList();

    public SpeciesTaxon(String scientificName) {
	super(SPECIES, scientificName);
    }

    public SpeciesTaxon(String scientificName, String vulgarName) {
	super(SPECIES, scientificName, vulgarName);
    }

    public void addSubspecies(SubspeciesTaxon... subspecies) {
	this.subspecies.addAll(Arrays.asList(subspecies));
    }

    public List<SubspeciesTaxon> getSubspecies() {
	return this.subspecies;
    }

}
