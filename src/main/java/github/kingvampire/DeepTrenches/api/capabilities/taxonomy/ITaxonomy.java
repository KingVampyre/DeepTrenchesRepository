package github.kingvampire.DeepTrenches.api.capabilities.taxonomy;

import github.kingvampire.DeepTrenches.api.taxonomy.FamilyTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.GenusTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.OrderTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.RankInstance;

public interface ITaxonomy {

    FamilyTaxon getFamily();

    GenusTaxon getGenus();

    OrderTaxon getOrder();

    SpeciesTaxon getSpecies();

    SubspeciesTaxon getSubspecies();

    RankInstance getTaxonomyInstance();

    void setTaxonomyInstance(RankInstance taxonomyInstance);

}
