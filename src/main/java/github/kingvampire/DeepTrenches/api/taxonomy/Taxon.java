package github.kingvampire.DeepTrenches.api.taxonomy;

import net.minecraftforge.registries.ForgeRegistryEntry;

public class Taxon extends ForgeRegistryEntry<Taxon> {

    private final String scientificName;
    private final TaxonType taxonType;
    private final String vulgarName;

    public Taxon(TaxonType taxonType, String name) {
	this(taxonType, name, name);
    }

    public Taxon(TaxonType taxonType, String scientificName, String vulgarName) {
	this.taxonType = taxonType;
	this.scientificName = scientificName;
	this.vulgarName = vulgarName;
    }

    public String getScientificName() {
	return this.scientificName;
    }

    public TaxonType getTaxonType() {
	return this.taxonType;
    }

    public String getVulgarName() {
	return this.vulgarName;
    }

}
