package github.kingvampire.DeepTrenches.core.taxonomy.species;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import net.minecraft.util.ResourceLocation;

public class LightLoosejawSpecies extends SpeciesTaxon {

    public LightLoosejawSpecies() {
	super("light_loosejaw");

	SubspeciesTaxon biglamp = new SubspeciesTaxon("biglamp");
	SubspeciesTaxon goodyears = new SubspeciesTaxon("goodyears");
	SubspeciesTaxon guernes = new SubspeciesTaxon("guernes");
	SubspeciesTaxon liems = new SubspeciesTaxon("liems");
	SubspeciesTaxon light = new SubspeciesTaxon("remarkable_light");
	SubspeciesTaxon smalllight = new SubspeciesTaxon("smalllight");

	biglamp.setRegistryName(new ResourceLocation(MODID, "biglamp_light_loosejaw"));
	goodyears.setRegistryName(new ResourceLocation(MODID, "goodyears_light_loosejaw"));
	guernes.setRegistryName(new ResourceLocation(MODID, "guernes_light_loosejaw"));
	liems.setRegistryName(new ResourceLocation(MODID, "liems_light_loosejaw"));
	light.setRegistryName(new ResourceLocation(MODID, "remarkable_light_loosejaw"));
	smalllight.setRegistryName(new ResourceLocation(MODID, "smalllight_loosejaw"));

	this.addSubspecies(biglamp, goodyears, guernes, liems, light, smalllight);
	this.setRegistryName(new ResourceLocation(MODID, "light_loosejaw"));
    }

}
