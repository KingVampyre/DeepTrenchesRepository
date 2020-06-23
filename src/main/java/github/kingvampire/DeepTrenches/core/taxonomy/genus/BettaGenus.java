package github.kingvampire.DeepTrenches.core.taxonomy.genus;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.taxonomy.GenusTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.core.taxonomy.species.BettaSplendidensSpecies;
import net.minecraft.util.ResourceLocation;

public class BettaGenus extends GenusTaxon {

    public BettaGenus() {
	super("betta");

	SpeciesTaxon bettaSplendidens = new BettaSplendidensSpecies();
	SpeciesTaxon bettaTrelosiagnus = new SpeciesTaxon("betta_trelosiagnus");

	bettaTrelosiagnus.setRegistryName(new ResourceLocation(MODID, "betta_trelosiagnus"));

	this.addSpecies(bettaSplendidens, bettaTrelosiagnus);
    }

}
