package github.kingvampire.DeepTrenches.api.taxonomy;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import net.minecraft.util.ResourceLocation;
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

	this.setRegistryName(new ResourceLocation(MODID, scientificName));

    }

    public String getScientificName() {
	return this.scientificName;
    }

    public TaxonType getTaxonType() {
	return this.taxonType;
    }

    public String getVulgarName() {
	return vulgarName;
    }

}
