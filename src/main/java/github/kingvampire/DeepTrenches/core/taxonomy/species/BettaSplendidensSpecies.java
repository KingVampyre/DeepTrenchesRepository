package github.kingvampire.DeepTrenches.core.taxonomy.species;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import net.minecraft.util.ResourceLocation;

public class BettaSplendidensSpecies extends SpeciesTaxon {

    public BettaSplendidensSpecies() {
	super("betta_splendidens");

	SubspeciesTaxon blue = new SubspeciesTaxon("blue");
	SubspeciesTaxon colorful = new SubspeciesTaxon("colorful");
	SubspeciesTaxon icarus_junior = new SubspeciesTaxon("icarus_junior");
	SubspeciesTaxon icarus = new SubspeciesTaxon("icarus");
	SubspeciesTaxon red = new SubspeciesTaxon("red");

	blue.setRegistryName(new ResourceLocation(MODID, "blue_betta"));
	colorful.setRegistryName(new ResourceLocation(MODID, "colorful_betta"));
	icarus_junior.setRegistryName(new ResourceLocation(MODID, "icarus_junior_betta"));
	icarus.setRegistryName(new ResourceLocation(MODID, "icarus_betta"));
	red.setRegistryName(new ResourceLocation(MODID, "red_betta"));

	this.addSubspecies(blue, colorful, icarus_junior, icarus, red);
	this.setRegistryName(new ResourceLocation(MODID, "betta_splendidens"));
    }

}
