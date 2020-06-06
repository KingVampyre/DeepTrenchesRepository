package github.kingvampire.DeepTrenches.core.taxonomy.species;

import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;

public class BarbeledLoosejawSpecies extends SpeciesTaxon {

    public BarbeledLoosejawSpecies() {
	super("barbeled_loosejaw");

	SubspeciesTaxon atlantic = new SubspeciesTaxon("atlantic");
	SubspeciesTaxon glowing = new SubspeciesTaxon("glowing");
	SubspeciesTaxon grimaldis = new SubspeciesTaxon("grimaldis");
	SubspeciesTaxon many_rayed = new SubspeciesTaxon("many_rayed");
	SubspeciesTaxon shiny = new SubspeciesTaxon("shiny");
	SubspeciesTaxon tittmanss = new SubspeciesTaxon("tittmanns");

	this.addSubspecies(atlantic, glowing, grimaldis, many_rayed, shiny, tittmanss);
    }

}
