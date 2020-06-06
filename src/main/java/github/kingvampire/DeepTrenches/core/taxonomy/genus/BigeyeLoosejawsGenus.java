package github.kingvampire.DeepTrenches.core.taxonomy.genus;

import github.kingvampire.DeepTrenches.api.taxonomy.GenusTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;

public class BigeyeLoosejawsGenus extends GenusTaxon {

    public BigeyeLoosejawsGenus() {
	super("pachystomias", "bigeye_loosejaws");

	this.addSpecies(new SpeciesTaxon("pachystomias_microdon", "smalltooth_dragonfish"));
    }

}
