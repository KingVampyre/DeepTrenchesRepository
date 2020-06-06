package github.kingvampire.DeepTrenches.api.capabilities.taxonomy;

import github.kingvampire.DeepTrenches.api.taxonomy.RankInstance;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class TaxonomyProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(ITaxonomy.class)
    public static final Capability<ITaxonomy> TAXONOMY_CAPABILITY = null;

    private final LazyOptional<ITaxonomy> taxonomy;

    public TaxonomyProvider() {
	this.taxonomy = LazyOptional.of(Taxonomy::new);
    }

    public TaxonomyProvider(RankInstance taxonomyRank) {
	this.taxonomy = LazyOptional.of(() -> new Taxonomy(taxonomyRank));
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
	TAXONOMY_CAPABILITY.readNBT(this.taxonomy.orElseThrow(IllegalArgumentException::new), null, nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
	return cap == TAXONOMY_CAPABILITY ? this.taxonomy.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
	return (CompoundNBT) TAXONOMY_CAPABILITY.writeNBT(this.taxonomy.orElseThrow(IllegalArgumentException::new),
		null);
    }

}
