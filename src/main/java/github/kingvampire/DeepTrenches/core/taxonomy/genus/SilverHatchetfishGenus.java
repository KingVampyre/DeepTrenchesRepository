package github.kingvampire.DeepTrenches.core.taxonomy.genus;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.taxonomy.GenusTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import net.minecraft.util.ResourceLocation;

public class SilverHatchetfishGenus extends GenusTaxon {

    public SilverHatchetfishGenus() {
	super("argyropelecus", "silver_hatchetfishes");

	SpeciesTaxon gigas = new SpeciesTaxon("argyropelecus_gigas", "giant_hatchetfish");

	gigas.setRegistryName(new ResourceLocation(MODID, "argyropelecus_gigas"));

	this.addSpecies(gigas);
    }

}
