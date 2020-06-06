package github.kingvampire.DeepTrenches.api.capabilities.taxonomy;

import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.Taxon;
import github.kingvampire.DeepTrenches.api.taxonomy.Rank;
import github.kingvampire.DeepTrenches.api.taxonomy.RankInstance;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class TaxonomyStorage implements IStorage<ITaxonomy> {

    @Override
    public void readNBT(Capability<ITaxonomy> capability, ITaxonomy instance, Direction side, INBT nbt) {
	CompoundNBT compound = (CompoundNBT) nbt;

	if (compound.contains("TaxonomyRank")) {

	    ResourceLocation taxonomyRankId = ResourceLocation.tryCreate(compound.getString("TaxonomyRank"));

	    if (taxonomyRankId != null) {
		IForgeRegistry<Rank> registry = GameRegistry.findRegistry(Rank.class);
		IForgeRegistry<Taxon> taxonRegistry = GameRegistry.findRegistry(Taxon.class);

		Rank taxonomyRank = registry.getValue(taxonomyRankId);

		if (taxonomyRank != null) {

		    if (compound.contains("Subspecies")) {
			ResourceLocation subspiciesId = ResourceLocation.tryCreate(compound.getString("Subspecies"));
			SubspeciesTaxon taxon = (SubspeciesTaxon) taxonRegistry.getValue(subspiciesId);

			instance.setTaxonomyInstance(new RankInstance(taxonomyRank, taxon));
		    } else
			instance.setTaxonomyInstance(new RankInstance(taxonomyRank));

		}
	    }
	}

    }

    @Override
    public INBT writeNBT(Capability<ITaxonomy> capability, ITaxonomy instance, Direction side) {
	CompoundNBT compound = new CompoundNBT();

	RankInstance taxonomyRank = instance.getTaxonomyInstance();

	if (taxonomyRank != null) {
	    compound.putString("TaxonomyRank", taxonomyRank.getTaxonomyRank().getRegistryName().toString());

	    SubspeciesTaxon subspecies = taxonomyRank.getSubspecies();

	    if (subspecies != null)
		compound.putString("Subspecies", subspecies.getRegistryName().toString());
	}

	return compound;
    }

}
