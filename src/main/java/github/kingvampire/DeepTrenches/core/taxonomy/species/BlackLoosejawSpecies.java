package github.kingvampire.DeepTrenches.core.taxonomy.species;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import net.minecraft.util.ResourceLocation;

public class BlackLoosejawSpecies extends SpeciesTaxon {

    public BlackLoosejawSpecies() {
	super("black_loosejaw");

	SubspeciesTaxon black = new SubspeciesTaxon("northern");
	SubspeciesTaxon southern = new SubspeciesTaxon("southern");

	black.setRegistryName(new ResourceLocation(MODID, "northern_black_loosejaw"));
	southern.setRegistryName(new ResourceLocation(MODID, "southern_black_loosejaw"));

	this.addSubspecies(black, southern);
	this.setRegistryName(new ResourceLocation(MODID, "black_loosejaw"));
    }

}
