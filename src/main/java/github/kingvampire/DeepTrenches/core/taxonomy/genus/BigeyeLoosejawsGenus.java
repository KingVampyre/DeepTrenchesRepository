package github.kingvampire.DeepTrenches.core.taxonomy.genus;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.taxonomy.GenusTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import net.minecraft.util.ResourceLocation;

public class BigeyeLoosejawsGenus extends GenusTaxon {

    public BigeyeLoosejawsGenus() {
	super("pachystomias", "bigeye_loosejaws");

	SpeciesTaxon microdon = new SpeciesTaxon("pachystomias_microdon", "smalltooth_dragonfish");

	microdon.setRegistryName(new ResourceLocation(MODID, "pachystomias_microdon"));

	this.addSpecies(microdon);
    }

}
