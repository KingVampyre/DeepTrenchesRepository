package github.kingvampire.DeepTrenches.api.capabilities.taxonomy;

import github.kingvampire.DeepTrenches.api.taxonomy.FamilyTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.GenusTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.OrderTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.RankInstance;

public class Taxonomy implements ITaxonomy {

    private RankInstance taxonomyRank;

    public Taxonomy() {
	// Default constructor
    }

    public Taxonomy(RankInstance taxonomyRank) {
	this.taxonomyRank = taxonomyRank;
    }

    @Override
    public FamilyTaxon getFamily() {
	return this.taxonomyRank.getFamily();
    }

    @Override
    public GenusTaxon getGenus() {
	return this.taxonomyRank.getGenus();
    }

    @Override
    public OrderTaxon getOrder() {
	return this.taxonomyRank.getOrder();
    }

    @Override
    public SpeciesTaxon getSpecies() {
	return this.taxonomyRank.getSpecies();
    }

    @Override
    public SubspeciesTaxon getSubspecies() {
	return this.taxonomyRank.getSubspecies();
    }

    @Override
    public RankInstance getTaxonomyInstance() {
	return this.taxonomyRank;
    }

    @Override
    public void setTaxonomyInstance(RankInstance taxonomyRank) {
	this.taxonomyRank = taxonomyRank;
    }

}
