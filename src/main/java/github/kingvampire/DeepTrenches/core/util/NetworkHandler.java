package github.kingvampire.DeepTrenches.core.util;

import static github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider.TAXONOMY_CAPABILITY;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;
import static net.minecraftforge.fml.network.PacketDistributor.TRACKING_ENTITY_AND_SELF;

import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.ITaxonomy;
import github.kingvampire.DeepTrenches.api.taxonomy.RankInstance;
import github.kingvampire.DeepTrenches.core.util.packets.TaxonomyCapabilityPacket;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@EventBusSubscriber
public class NetworkHandler {

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, "main"),
	    () -> "1", "1"::equals, "1"::equals);

    @SubscribeEvent
    public static void startTrackingTaxonomyCapability(PlayerEvent.StartTracking event) {
	Entity entity = event.getTarget();
	LazyOptional<ITaxonomy> taxonomy = entity.getCapability(TAXONOMY_CAPABILITY);

	if (taxonomy.isPresent() && !entity.world.isRemote()) {
	    ITaxonomy itaxonomy = taxonomy.orElseThrow(IllegalArgumentException::new);

	    if (entity instanceof CreatureEntity) {
		CreatureEntity creature = (CreatureEntity) entity;
		RankInstance taxonomyRank = itaxonomy.getTaxonomyInstance();

		TaxonomyCapabilityPacket packet = new TaxonomyCapabilityPacket(creature, taxonomyRank);

		INSTANCE.send(TRACKING_ENTITY_AND_SELF.with(() -> entity), packet);
	    }
	}
    }

}
