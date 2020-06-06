package github.kingvampire.DeepTrenches.core.taxonomy.species;

import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;

public class BettaSplendidensSpecies extends SpeciesTaxon {

    public BettaSplendidensSpecies() {
	super("betta_splendidens");

	SubspeciesTaxon blue = new SubspeciesTaxon("blue");
	SubspeciesTaxon colorful = new SubspeciesTaxon("colorful");
	SubspeciesTaxon icarus_junior = new SubspeciesTaxon("icarus_junior");
	SubspeciesTaxon icarus = new SubspeciesTaxon("icarus");
	SubspeciesTaxon red = new SubspeciesTaxon("red");

	this.addSubspecies(blue, colorful, icarus_junior, icarus, red);
    }

}
