package github.kingvampire.DeepTrenches.api.taxonomy;

import static github.kingvampire.DeepTrenches.api.taxonomy.TaxonType.ORDER;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

public class OrderTaxon extends Taxon {

    public List<FamilyTaxon> families = Lists.newArrayList();

    public OrderTaxon(String scientificName) {
	super(ORDER, scientificName);
    }

    public OrderTaxon addFamily(FamilyTaxon... families) {
	this.families.addAll(Arrays.asList(families));

	return this;
    }

    public List<FamilyTaxon> getFamilies() {
	return this.families;
    }

}
