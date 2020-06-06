package github.kingvampire.DeepTrenches.core.taxonomy.genus;

import github.kingvampire.DeepTrenches.api.taxonomy.GenusTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.core.taxonomy.species.BettaSplendidensSpecies;

public class BettaGenus extends GenusTaxon {

    public BettaGenus() {
	super("betta");

	SpeciesTaxon bettaSplendidens = new BettaSplendidensSpecies();
	SpeciesTaxon bettaTrelosiagnus = new SpeciesTaxon("betta_trelosiagnus");

	this.addSpecies(bettaSplendidens, bettaTrelosiagnus);
    }

}
