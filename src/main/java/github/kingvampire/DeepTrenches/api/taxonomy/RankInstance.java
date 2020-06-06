package github.kingvampire.DeepTrenches.api.taxonomy;

public class RankInstance {

    private SubspeciesTaxon subspecies;
    private Rank taxonomyRank;

    public RankInstance(Rank taxonomyRank, SubspeciesTaxon subspecies) {
	this.subspecies = subspecies;
	this.taxonomyRank = taxonomyRank;
    }

    public RankInstance(Rank taxonomyRank) {
	this.taxonomyRank = taxonomyRank;
    }

    public FamilyTaxon getFamily() {
	return this.taxonomyRank.getFamily();
    }

    public GenusTaxon getGenus() {
	return this.taxonomyRank.getGenus();
    }

    public OrderTaxon getOrder() {
	return this.taxonomyRank.getOrder();
    }

    public SpeciesTaxon getSpecies() {
	return this.taxonomyRank.getSpecies();
    }

    public SubspeciesTaxon getSubspecies() {
	return this.subspecies;
    }

    public Rank getTaxonomyRank() {
	return this.taxonomyRank;
    }

    public boolean hasSubspecies() {
	return this.subspecies != null && this.taxonomyRank.hasSubspecies();
    }

    public void setSubspecies(SubspeciesTaxon subspecies) {
	this.subspecies = subspecies;
    }

}
