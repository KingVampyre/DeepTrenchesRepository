package github.kingvampire.DeepTrenches.api.taxonomy;

import static github.kingvampire.DeepTrenches.api.taxonomy.TaxonType.FAMILY;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.util.ResourceLocation;

public class FamilyTaxon extends Taxon {

    private List<GenusTaxon> genuses = Lists.newArrayList();

    public FamilyTaxon(String scientificName) {
	super(FAMILY, scientificName);

	this.setRegistryName(new ResourceLocation(MODID, scientificName));
    }

    public FamilyTaxon(String scientificName, String vulgarName) {
	super(FAMILY, scientificName, vulgarName);

	this.setRegistryName(new ResourceLocation(MODID, scientificName));
    }

    public FamilyTaxon addGenus(GenusTaxon... genuses) {
	this.genuses.addAll(Arrays.asList(genuses));

	return this;
    }

    public List<GenusTaxon> getGenuses() {
	return this.genuses;
    }

}
