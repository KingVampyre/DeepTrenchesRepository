package github.kingvampire.DeepTrenches.core.taxonomy.genus;

import github.kingvampire.DeepTrenches.api.taxonomy.GenusTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;

public class SilverHatchetfishGenus extends GenusTaxon {

    public SilverHatchetfishGenus() {
	super("argyropelecus", "silver_hatchetfishes");

	this.addSpecies(new SpeciesTaxon("argyropelecus_gigas", "giant_hatchetfish"));
    }

}
