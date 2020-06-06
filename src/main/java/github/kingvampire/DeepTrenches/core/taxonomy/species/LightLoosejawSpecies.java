package github.kingvampire.DeepTrenches.core.taxonomy.species;

import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;

public class LightLoosejawSpecies extends SpeciesTaxon {

    public LightLoosejawSpecies() {
	super("light_loosejaw");

	SubspeciesTaxon biglamp = new SubspeciesTaxon("biglamp");
	SubspeciesTaxon goodyears = new SubspeciesTaxon("goodyears");
	SubspeciesTaxon guernes = new SubspeciesTaxon("guernes");
	SubspeciesTaxon liems = new SubspeciesTaxon("liems");
	SubspeciesTaxon light = new SubspeciesTaxon("remarkable_light");
	SubspeciesTaxon smalllight = new SubspeciesTaxon("smalllight");

	this.addSubspecies(biglamp, goodyears, guernes, liems, light, smalllight);
    }

}
