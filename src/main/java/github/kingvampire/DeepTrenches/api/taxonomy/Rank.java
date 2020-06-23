package github.kingvampire.DeepTrenches.api.taxonomy;

import java.util.List;
import java.util.Random;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class Rank extends ForgeRegistryEntry<Rank> {

    public static class Builder {

	private FamilyTaxon family;
	private GenusTaxon genus;
	private OrderTaxon order;
	private SpeciesTaxon species;

	public Rank build(ResourceLocation id) {
	    Rank rank = new Rank(this.order, this.family, this.genus, this.species);

	    return rank.setRegistryName(id);
	}

	public Builder family(FamilyTaxon family) {
	    this.family = family;

	    return this;
	}

	public Builder genus(GenusTaxon genus) {
	    this.genus = genus;

	    return this;
	}

	public Builder order(OrderTaxon order) {
	    this.order = order;

	    return this;
	}

	public Builder species(SpeciesTaxon species) {
	    this.species = species;

	    return this;
	}

    }

    private final FamilyTaxon family;
    private final GenusTaxon genus;
    private final OrderTaxon order;
    private final SpeciesTaxon species;

    public Rank(OrderTaxon order, FamilyTaxon family, GenusTaxon genus, SpeciesTaxon species) {
	this.family = family;
	this.genus = genus;
	this.order = order;
	this.species = species;
    }

    public RankInstance getDefaultInstance() {
	return new RankInstance(this);
    }

    public FamilyTaxon getFamily() {
	return this.family;
    }

    public GenusTaxon getGenus() {
	return this.genus;
    }

    public OrderTaxon getOrder() {
	return this.order;
    }

    public RankInstance getRandomInstance() {
	return new RankInstance(this, this.hasSubspecies() ? this.getRandomSubspecies() : null);
    }

    public SubspeciesTaxon getRandomSubspecies() {
	List<SubspeciesTaxon> subspecies = this.species.getSubspecies();

	return subspecies.get(new Random().nextInt(subspecies.size()));
    }

    public SpeciesTaxon getSpecies() {
	return this.species;
    }

    public boolean hasSubspecies() {
	return !this.species.getSubspecies().isEmpty();
    }

}
