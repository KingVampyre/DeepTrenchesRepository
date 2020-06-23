package github.kingvampire.DeepTrenches.api.capabilities.taxonomy;

import static github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider.TAXONOMY_CAPABILITY;
import static net.minecraftforge.fml.network.PacketDistributor.TRACKING_ENTITY_AND_SELF;

import github.kingvampire.DeepTrenches.api.capabilities.IPacketSender;
import github.kingvampire.DeepTrenches.api.taxonomy.FamilyTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.GenusTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.OrderTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.RankInstance;
import github.kingvampire.DeepTrenches.api.taxonomy.SpeciesTaxon;
import github.kingvampire.DeepTrenches.api.taxonomy.SubspeciesTaxon;
import github.kingvampire.DeepTrenches.core.util.NetworkHandler;
import github.kingvampire.DeepTrenches.core.util.packets.CapabilityPacket;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;

public interface ITaxonomy extends IPacketSender {

    FamilyTaxon getFamily();

    GenusTaxon getGenus();

    OrderTaxon getOrder();

    SpeciesTaxon getSpecies();

    SubspeciesTaxon getSubspecies();

    RankInstance getTaxonomyInstance();

    @Override
    default void sendPacket(Entity entity) {
	CompoundNBT compound = (CompoundNBT) TAXONOMY_CAPABILITY.writeNBT(this, null);
	CapabilityPacket packet = new CapabilityPacket(TAXONOMY_CAPABILITY, entity, compound);

	NetworkHandler.INSTANCE.send(TRACKING_ENTITY_AND_SELF.with(() -> entity), packet);
    }

    void setTaxonomyInstance(RankInstance taxonomyInstance);

}
