package github.kingvampire.DeepTrenches.core.taxonomy.species;

import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;

public class BlackLoosejawSpecies extends SpeciesTaxon {

    public BlackLoosejawSpecies() {
	super("black_loosejaw");

	SubspeciesTaxon black = new SubspeciesTaxon("northern");
	SubspeciesTaxon southern = new SubspeciesTaxon("southern");

	this.addSubspecies(black, southern);
    }

}
