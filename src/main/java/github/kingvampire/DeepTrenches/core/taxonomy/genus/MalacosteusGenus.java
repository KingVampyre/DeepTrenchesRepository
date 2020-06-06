package github.kingvampire.DeepTrenches.core.taxonomy.genus;

import github.kingvampire.DeepTrenches.api.taxonomy.GenusTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.core.taxonomy.species.BlackLoosejawSpecies;
import github.kingvampire.DeepTrenches.core.taxonomy.species.LightLoosejawSpecies;
import github.kingvampire.DeepTrenches.core.taxonomy.species.BarbeledLoosejawSpecies;

public class MalacosteusGenus extends GenusTaxon {

    public MalacosteusGenus() {
	super("malacosteus");

	SpeciesTaxon blackLoosejaw = new BlackLoosejawSpecies();
	SpeciesTaxon lightLoosejaw = new LightLoosejawSpecies();
	SpeciesTaxon loosejaw = new BarbeledLoosejawSpecies();

	this.addSpecies(blackLoosejaw, lightLoosejaw, loosejaw);
    }

}
