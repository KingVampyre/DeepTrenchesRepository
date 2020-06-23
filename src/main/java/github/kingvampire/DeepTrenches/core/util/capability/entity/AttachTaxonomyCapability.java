package github.kingvampire.DeepTrenches.core.util.capability.entity;

import static github.kingvampire.DeepTrenches.core.init.ModEntities.BARBELED_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.BLACK_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.DEEP_LAKE_BETTA;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.GIANT_HATCHETFISH;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.LIGHT_LOOSEJAW;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.SMALLTOOTH_DRAGONFISH;
import static github.kingvampire.DeepTrenches.core.init.ModEntities.STASP;
import static github.kingvampire.DeepTrenches.core.util.Constants.MODID;

import github.kingvampire.DeepTrenches.api.capabilities.taxonomy.TaxonomyProvider;
import github.kingvampire.DeepTrenches.core.init.ModTaxonomyRanks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class AttachTaxonomyCapability {

    @SubscribeEvent
    public static void attach(AttachCapabilitiesEvent<Entity> event) {
	Entity entity = event.getObject();
	EntityType<?> type = entity.getType();
	
	ResourceLocation id = new ResourceLocation(MODID, "taxonomy");

	if (type == BARBELED_LOOSEJAW)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.BARBELED_LOOSEJAW.getRandomInstance()));

	else if (type == BETTA)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.BETTA.getRandomInstance()));

	else if (type == BLACK_LOOSEJAW)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.BLACK_LOOSEJAW.getRandomInstance()));

	else if (type == DEEP_LAKE_BETTA)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.DEEP_LAKE_BETTA));

	else if (type == GIANT_HATCHETFISH)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.GIANT_HATCHETFISH));

	else if (type == LIGHT_LOOSEJAW)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.LIGHT_LOOSEJAW.getRandomInstance()));

	else if (type == SMALLTOOTH_DRAGONFISH)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.SMALLTOOTH_DRAGONFISH.getRandomInstance()));

	else if (type == STASP)
	    event.addCapability(id, new TaxonomyProvider(ModTaxonomyRanks.STASP.getRandomInstance()));

    }

}
