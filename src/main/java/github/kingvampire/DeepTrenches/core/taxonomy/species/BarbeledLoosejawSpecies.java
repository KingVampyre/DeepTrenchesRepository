package github.kingvampire.DeepTrenches.core.taxonomy.species;

import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import net.minecraft.util.ResourceLocation;

public class BarbeledLoosejawSpecies extends SpeciesTaxon {

    public BarbeledLoosejawSpecies() {
	super("barbeled_loosejaw");

	SubspeciesTaxon atlantic = new SubspeciesTaxon("atlantic");
	SubspeciesTaxon glowing = new SubspeciesTaxon("glowing");
	SubspeciesTaxon grimaldis = new SubspeciesTaxon("grimaldis");
	SubspeciesTaxon many_rayed = new SubspeciesTaxon("many_rayed");
	SubspeciesTaxon shiny = new SubspeciesTaxon("shiny");
	SubspeciesTaxon tittmanss = new SubspeciesTaxon("tittmanns");

	atlantic.setRegistryName(new ResourceLocation(MODID, "atlantic_barbeled_loosejaw"));
	glowing.setRegistryName(new ResourceLocation(MODID, "glowing_barbeled_loosejaw"));
	grimaldis.setRegistryName(new ResourceLocation(MODID, "grimaldis_barbeled_loosejaw"));
	many_rayed.setRegistryName(new ResourceLocation(MODID, "many_rayed_barbeled_loosejaw"));
	shiny.setRegistryName(new ResourceLocation(MODID, "shiny_barbeled_loosejaw"));
	tittmanss.setRegistryName(new ResourceLocation(MODID, "tittmanss_barbeled_loosejaw"));

	this.addSubspecies(atlantic, glowing, grimaldis, many_rayed, shiny, tittmanss);
	this.setRegistryName(new ResourceLocation(MODID, "barbeled_loosejaw"));
    }

}
