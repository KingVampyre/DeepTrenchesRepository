package github.kingvampire.DeepTrenches.core.util.packets;

import static github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider.TAXONOMY_CAPABILITY;

import java.util.function.Supplier;

import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.ITaxonomy;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.Taxon;
import github.kingvampire.DeepTrenches.api.taxonomy.Rank;
import github.kingvampire.DeepTrenches.api.taxonomy.RankInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import net.minecraftforge.registries.IForgeRegistry;

public class TaxonomyCapabilityPacket {

    public static TaxonomyCapabilityPacket decode(PacketBuffer buffer) {
	World world = Minecraft.getInstance().world;

	CreatureEntity creature = (CreatureEntity) world.getEntityByID(buffer.readInt());
	ResourceLocation taxonomyRankId = ResourceLocation.tryCreate(buffer.readString());
	ResourceLocation subspiciesId = ResourceLocation.tryCreate(buffer.readString());

	if (taxonomyRankId != null) {
	    IForgeRegistry<Rank> registry = GameRegistry.findRegistry(Rank.class);
	    IForgeRegistry<Taxon> taxonRegistry = GameRegistry.findRegistry(Taxon.class);

	    Rank taxonomyRank = registry.getValue(taxonomyRankId);
	    SubspeciesTaxon taxon = (SubspeciesTaxon) taxonRegistry.getValue(subspiciesId);

	    if (taxonomyRank != null) {
		TaxonomyCapabilityPacket packet = new TaxonomyCapabilityPacket(creature, null);

		if (taxon != null)
		    packet.setTaxonomyRank(new RankInstance(taxonomyRank, taxon));
		else
		    packet.setTaxonomyRank(new RankInstance(taxonomyRank));

		return packet;
	    }
	}

	return null;
    }

    public static void encode(TaxonomyCapabilityPacket msg, PacketBuffer buffer) {
	RankInstance taxonomyRankInstance = msg.getTaxonomyRankInstance();
	SubspeciesTaxon subspecies = taxonomyRankInstance.getSubspecies();

	buffer.writeInt(msg.getCreature().getEntityId());
	buffer.writeString(taxonomyRankInstance.getTaxonomyRank().getRegistryName().toString());
	buffer.writeString(subspecies != null ? subspecies.getRegistryName().toString() : "");

    }

    public static void handle(TaxonomyCapabilityPacket msg, Supplier<NetworkEvent.Context> ctx) {
	Context context = ctx.get();
	NetworkDirection direction = context.getDirection();

	context.enqueueWork(() -> {
	    LazyOptional<ITaxonomy> taxonomy = msg.getCreature().getCapability(TAXONOMY_CAPABILITY);

	    if (taxonomy.isPresent()) {
		ITaxonomy itaxonomy = taxonomy.orElseThrow(IllegalArgumentException::new);

		if (direction.getReceptionSide().isClient() && direction.getOriginationSide().isServer())
		    itaxonomy.setTaxonomyInstance(msg.getTaxonomyRankInstance());
	    }
	});

	context.setPacketHandled(true);
    }

    private CreatureEntity creature;
    private RankInstance taxonomyRank;

    public TaxonomyCapabilityPacket(CreatureEntity creature, RankInstance taxonomyRank) {
	this.creature = creature;
	this.taxonomyRank = taxonomyRank;
    }

    public CreatureEntity getCreature() {
	return creature;
    }

    public RankInstance getTaxonomyRankInstance() {
	return this.taxonomyRank;
    }

    public void setTaxonomyRank(RankInstance taxonomyRank) {
	this.taxonomyRank = taxonomyRank;
    }
}
