package github.kingvampire.DeepTrenches.api.taxonomy;

import static github.kingvampire.DeepTrenches.api.taxonomy.TaxonType.ORDER;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.util.ResourceLocation;

public class OrderTaxon extends Taxon {

    public List<FamilyTaxon> families = Lists.newArrayList();

    public OrderTaxon(String scientificName) {
	super(ORDER, scientificName);

	this.setRegistryName(new ResourceLocation(MODID, scientificName));
    }

    public OrderTaxon addFamily(FamilyTaxon... families) {
	this.families.addAll(Arrays.asList(families));

	return this;
    }

    public List<FamilyTaxon> getFamilies() {
	return this.families;
    }

}
