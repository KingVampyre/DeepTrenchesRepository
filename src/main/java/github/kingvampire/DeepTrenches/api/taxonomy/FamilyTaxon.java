package github.kingvampire.DeepTrenches.api.taxonomy;

import static github.kingvampire.DeepTrenches.api.taxonomy.TaxonType.FAMILY;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

public class FamilyTaxon extends Taxon {

    private List<GenusTaxon> genuses = Lists.newArrayList();

    public FamilyTaxon(String scientificName) {
	super(FAMILY, scientificName);
    }

    public FamilyTaxon(String scientificName, String vulgarName) {
	super(FAMILY, scientificName, vulgarName);
    }

    public FamilyTaxon addGenus(GenusTaxon... genuses) {
	this.genuses.addAll(Arrays.asList(genuses));

	return this;
    }

    public List<GenusTaxon> getGenuses() {
	return this.genuses;
    }

}
