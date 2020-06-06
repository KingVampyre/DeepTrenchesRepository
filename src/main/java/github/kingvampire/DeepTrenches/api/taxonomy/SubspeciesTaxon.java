package github.kingvampire.DeepTrenches.api.taxonomy;

import static github.kingvampire.DeepTrenches.api.taxonomy.TaxonType.SUBSPECIES;

public class SubspeciesTaxon extends Taxon {

    public SubspeciesTaxon(String name) {
	super(SUBSPECIES, name);
    }

}
